package org.fiteagle.interactors.monitoring.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.fiteagle.interactors.monitoring.MonitoringManager;

public class OMLServer implements Runnable {
	

	private boolean running = true;
	
	public void terminate(){
		this.running = false;
	}
	
	public void run() {
		int portNumber = 3434;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		};
		
		Socket socket = null;
		while (running) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			(new Thread(new ClientHandler(socket))).start();
		}
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		
	}


}
