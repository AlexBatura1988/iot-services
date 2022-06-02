package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import dbService.DBService;

public class InventoryServer extends Thread {
	private final int PORT = 9090;
	private boolean isRunning = true;
	private final int POOL_SIZE = 10;
	private final int SOKET_TIMEOUT_MILLIS = 10000;

	public void run() {

		ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
		ServerSocket serverSocket = null;
		DBService DBService = new DBService();
		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout(SOKET_TIMEOUT_MILLIS);
			System.out.println("Server connected");
			while (isRunning) {
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(SOKET_TIMEOUT_MILLIS);
				executorService.execute(new InventoryServerThread(socket, DBService));
			}
		} catch (SocketTimeoutException e) {
			System.out.println("Receiving stopped");
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + PORT);
			e.printStackTrace();
		} finally {
			if (serverSocket != null)
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			executorService.shutdown();
		}
	}

	public static void main(String[] args) {
		InventoryServer inventoryServer = new InventoryServer();
		inventoryServer.start();
	}
}
