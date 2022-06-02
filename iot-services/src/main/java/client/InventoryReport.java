package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import models.IOTThing;
import server.InventoryServer;

public class InventoryReport implements Runnable {
	static ScheduledThreadPoolExecutor threadPoolExecutor;
	private IOTThing iotThing;
	private final static String SERVER_NAME = "localhost";
	private final static int SERVER_PORT = 9090;
	Gson gson;

	public InventoryReport(IOTThing iotThing) {
		this.iotThing = iotThing;
		gson = new Gson();
	}

	public void run() {
		iotThing.simulateInventoryChange();
		try (Socket clientSocket = new Socket(SERVER_NAME, SERVER_PORT);
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader bufferReader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()))) {
			
			writer.println(gson.toJson(iotThing));
			
			String line = bufferReader.readLine();
			System.out.println("from server : " + line);
		} catch (UnknownHostException e) {
			System.err.println("Server is not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Socket failed");
			e.printStackTrace();
		}
	}

	
	
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
       // creating InventoryReports, each has its own new IotThing
        InventoryReport inventoryReport1 = new InventoryReport(new IOTThing("Model A", new ArrayList<>()));
        InventoryReport inventoryReport2 = new InventoryReport(new IOTThing("Model B", new ArrayList<>()));
        InventoryReport inventoryReport3 = new InventoryReport(new IOTThing("Model C", new ArrayList<>()));
        InventoryReport inventoryReport4 = new InventoryReport(new IOTThing("Model D", new ArrayList<>()));

        // schedule InventoryReports to send new state every 10 seconds
        threadPoolExecutor.scheduleAtFixedRate(inventoryReport1, 1, 10, TimeUnit.SECONDS);
        threadPoolExecutor.scheduleAtFixedRate(inventoryReport2, 3, 10, TimeUnit.SECONDS);
        threadPoolExecutor.scheduleAtFixedRate(inventoryReport3, 5, 10, TimeUnit.SECONDS);
        threadPoolExecutor.scheduleAtFixedRate(inventoryReport4, 7, 10, TimeUnit.SECONDS);
		
	}

}
