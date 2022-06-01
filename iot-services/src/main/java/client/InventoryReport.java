package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import models.IOTThing;

public class InventoryReport implements Runnable {
	
	private IOTThing iotThing;
    private final static String SERVER_NAME = "localhost";
    private final static int SERVER_PORT = 9090;

    public InventoryReport(IOTThing iotThing) {
        this.iotThing = iotThing;
    }

    public void run() {
        try (Socket clientSocket = new Socket(SERVER_NAME, SERVER_PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            System.out.println("Connected to server");
            
			writer.println("Hello from the other side");
           
            String line = bufferReader.readLine();
            System.out.println("server says: " + line);
        } catch (UnknownHostException e) {
            System.err.println("Server is not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Socket failed");
            e.printStackTrace();
        }
    }

}
