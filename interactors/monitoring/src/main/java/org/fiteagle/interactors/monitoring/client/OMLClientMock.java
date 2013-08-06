package org.fiteagle.interactors.monitoring.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OMLClientMock {
	
	String hostName = "localhost";
	int portNumber = 3434;
	String pathToPushOMLString = "/pushOMLString.txt";
	
	
	
	
	public OMLClientMock() {
	}
	
	public OMLClientMock(String hostName, int portNumber) {
		this.hostName = hostName;
		this.portNumber = portNumber;
	}
	
	
	public void run(){
		
		Socket socket = null;
		PrintWriter out = null;
		
		InputStream inputStream = this.getClass().getResourceAsStream(pathToPushOMLString);
		BufferedReader in= new BufferedReader(new InputStreamReader(inputStream));
		try {
			socket = new Socket("localhost",3434);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		String line;
		try {
			while ((line=in.readLine()) != null) {
				out.println(line);
				out.flush();
			}
		} catch (IOException e1) {
			throw new RuntimeException(e1.getMessage());
		}
		
		out.close();
		
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
