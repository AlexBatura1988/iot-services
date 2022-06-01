package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InventoryServer implements Runnable {

	private final int PORT = 9090;
	private boolean isRunning = true;
	private final int POOL_SIZE = 10;

	public void run() {
		ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server connected");
			while (isRunning) {
				Socket socket = serverSocket.accept();
				executorService.execute(new InventoryServerThread(socket));
			}
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

	public void stop() {
		isRunning = false;
	}

}
