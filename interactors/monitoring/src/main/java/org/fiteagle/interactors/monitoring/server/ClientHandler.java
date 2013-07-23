package org.fiteagle.interactors.monitoring.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.fiteagle.interactors.monitoring.MonitoringManager;
import orgt.fiteagle.core.monitoring.StatusTable;

public class ClientHandler implements Runnable {
	Socket socket;
	long timeForOldLastCheckedInMilis = 15778463000L;
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
			boolean oneComponentIsUp = false;
			boolean upAndLastCheckedOld = false;
			Date lastChecked = new Date();
			while ((str=in.readLine()) != null) {
				StatusTable componentStatusTable = new StatusTable();
				
				if(str.contains("domain:"))
					testbedName=str.split(":")[1].trim();
				
				if(str.length()>0 && Character.isDigit(str.charAt(0))){
					String[] strArray = parseLine(str);
					Date lastCheckedDate = new Date();
					if(strArray[3]!=null)
					lastCheckedDate = parseStringToDate(strArray[3]);
					
					componentStatusTable.setLastCheck(lastCheckedDate);
					componentStatusTable.setId(strArray[1]);
					
					if(strArray[2].compareTo("1")==0){
						if(isLastCheckedOld(lastCheckedDate)){
							upAndLastCheckedOld = true;
							componentStatusTable.setStatus("upAndLastCheckedOld");
							//TODO: if lastchecked is old grey button =>set upAndLastCheckedOld=> status upAndOld.
						}else
							componentStatusTable.setStatus("up");
						oneComponentIsUp=true;
						if(testbedStatus.compareTo("partially")!=0)
							testbedStatus="up";
					}
					
					if(strArray[2].compareTo("0")==0){
						componentStatusTable.setStatus("down");
						testbedStatus = "partially";
					}
					
//					if(statusTable.getStatus()==null)
//						testbedStatus = "undefined";
					
					if(lastCheckedDate.before(lastChecked))
						lastChecked=lastCheckedDate;
					
					statusTable.addComponent(componentStatusTable);
					
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
			
			if(!oneComponentIsUp)
				testbedStatus = "down";
			
			statusTable.setLastCheck(lastChecked);
			if(testbedStatus.compareTo("up")==0 && upAndLastCheckedOld)
				testbedStatus="upAndLastCheckedOld";
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
	
	
	private boolean isLastCheckedOld(Date lastCheckedDate) {
		Date now = new Date();
		long nowInMilis = now.getTime();
		long lastCheckedDateInMilis = lastCheckedDate.getTime();
		
		return (nowInMilis-lastCheckedDateInMilis)>timeForOldLastCheckedInMilis;
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
