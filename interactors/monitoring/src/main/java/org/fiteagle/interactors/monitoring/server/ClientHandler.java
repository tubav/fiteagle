package org.fiteagle.interactors.monitoring.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.fiteagle.interactors.monitoring.MonitoringManager;
import orgt.fiteagle.core.monitoring.StatusTable;

public class ClientHandler implements Runnable {
	Socket socket;
//	PrintStream out;

	public ClientHandler(Socket s) {
		socket = s;
	}

	@Override
	public void run() {
		BufferedReader in;
		PrintWriter out = null;
		StatusTable statusTable = new StatusTable();
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String str;
			String testbedName = null;
			String testbedStatus = "undefined";
			Date lastChecked = new Date();
			while ((str=in.readLine()) != null) {
				
				if(str.contains("domain:"))
					testbedName=str.split(":")[1].trim();
				
				if(str.length()>0 && Character.isDigit(str.charAt(0))){
					String[] strArray = parseLine(str);
					Date date = new Date();
					if(strArray[3]!=null)
					date = parseStringToDate(strArray[3]);
					statusTable.setId(strArray[1]);
					if(strArray[2].compareTo("1")==0 && testbedStatus.compareTo("down")!=0)
						testbedStatus="up";
					if(strArray[2].compareTo("0")==0)
						testbedStatus = "down";
					
//					if(statusTable.getStatus()==null)
//						testbedStatus = "undefined";
					
					if(date.before(lastChecked))
						lastChecked=date;
					
				}
				
//				if(str.length()>0 && Character.isDigit(str.charAt(0))){
//					String[] strArray = parseLine(str);
//					
//					Date date = parseStringToDate(strArray[3]);
//					
//					StatusTable statusTable = new StatusTable();
////					statusTable.setXipiId(strArray[0]);
//					statusTable.setId(strArray[1]);
//					if(strArray[2].compareTo("1")==0)
//						statusTable.setStatus("up");
//					if(strArray[2].compareTo("0")==0)
//						statusTable.setStatus("down");
//					
//					if(statusTable.getStatus()==null)
//						statusTable.setStatus("undefined");
//					
//					statusTable.setLastCheck(date);
//					new MonitoringManager().pushMonitoringData(statusTable);
//					
////					System.out.println("1:'"+strArray[0]+"' 2:'"+strArray[1]+"' 3:'"+date+"'");
//				}
				
//				if (str != null && str.compareTo("quit")==0) break;
			}
			statusTable.setLastCheck(lastChecked);
			statusTable.setStatus(testbedStatus);
			if(testbedName!=null){
				statusTable.setId(testbedName);
				new MonitoringManager().pushMonitoringData(statusTable);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
			out.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		

//		try {
//			out = new PrintStream(socket.getOutputStream());
//		} catch (IOException e) {
//			System.out.println("PrintStream Error");
//		}
//
//		out.println("Hello");
//
//		try {
//			socket.close();
//		} catch (IOException e) {
//			System.out.println("Failed to close, oddly...");
//		}
	}
	
	
	private Date parseStringToDate(String dateString) throws ParseException {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSz");
//		DateFormat dateFormat = DateFormat.getInstance();
//		Date date = dateFormat.parse(dateString);
		
		
		StringBuilder dateStrBuilder = new StringBuilder(dateString);
		dateStrBuilder.deleteCharAt(dateString.lastIndexOf(":"));
		
//		String test = "2013-07-21T18:33:38.546057-0700";
//		Date testDate = simpleDate.parse(test);
//		System.out.println("here test: "+testDate);
//		
//		return simpleDate.parse(dateString);
		return simpleDate.parse(dateStrBuilder.toString());
//		return date;
		
	}


	private String[] parseLine(String str) {
		if (str==null) return null;
		
		String[] strArr = str.split("\t");
//		for (int i = 0; i < strArr.length; i++) {
//			System.out.println("strArrTest: "+strArr[i]);
//		}
		String[] response = new String[4];
		
		response[0]=strArr[1].trim();
		response[1]=strArr[3].trim();
		response[2]=strArr[4].trim();
		response[3]=strArr[5].trim();
		
		return response;
	}
}
