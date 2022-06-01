package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InventoryServerThread implements Runnable {

	Socket clientSocket;
	private boolean stopped;

	public InventoryServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

			System.out
					.println("Thing is connected " );
			
			String line = bufferReader.readLine();
			System.out.println("Thing says: " + line);

			
			writer.println("processing result done ");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void kill() {
		stopped = true;
	}

}
