package org.fiteagle.interactors.monitoring.server;

import java.io.BufferedReader;
import java.io.File;
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
					
					if(lastCheckedDate.before(lastChecked))
						lastChecked=lastCheckedDate;
					
					statusTable.addComponent(componentStatusTable);
					
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
		
	}
	
	
	private boolean isLastCheckedOld(Date lastCheckedDate) {
		Date now = new Date();
		long nowInMilis = now.getTime();
		long lastCheckedDateInMilis = lastCheckedDate.getTime();
		
		return (nowInMilis-lastCheckedDateInMilis)>timeForOldLastCheckedInMilis;
	}

	private Date parseStringToDate(String dateString) throws ParseException {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSz");
		
		StringBuilder dateStrBuilder = new StringBuilder(dateString);
		dateStrBuilder.deleteCharAt(dateString.lastIndexOf(":"));
		
		return simpleDate.parse(dateStrBuilder.toString());
		
	}


	private String[] parseLine(String str) {
		if (str==null) return null;
		
		String[] strArr = str.split("\t");
		String[] response = new String[4];
		
		response[0]=strArr[1].trim();
		response[1]=strArr[3].trim();
		response[2]=strArr[4].trim();
		response[3]=strArr[5].trim();
		
		return response;
	}
}
