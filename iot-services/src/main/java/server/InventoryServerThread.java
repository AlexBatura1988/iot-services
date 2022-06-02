package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import dbService.DBService;
import models.IOTThing;

public class InventoryServerThread implements Runnable {

	private Socket clientSocket;
    private  DBService dbService;

    public InventoryServerThread(Socket clientSocket, DBService dbService) {
        this.clientSocket = clientSocket;
        this.dbService = dbService;
    }

    @Override
    public void run() {
        try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
            System.out.println(
                    "Thing is connected " + clientSocket.getInetAddress());

            // reading data
            String json = bufferReader.readLine();
            System.out.println("Json received from IOTThing: " + json);
            IOTThing iotThing = new Gson().fromJson(json, IOTThing.class);
            dbService.updateIotThingInDB(iotThing);

            // sending data
            writer.println("processing result done ");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
