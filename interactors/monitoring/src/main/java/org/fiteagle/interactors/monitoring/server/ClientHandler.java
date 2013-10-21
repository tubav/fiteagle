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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.fiteagle.core.monitoring.StatusTable;
import org.fiteagle.interactors.monitoring.MonitoringManager;

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
		HashMap <Integer, String> componentSchemaNames = new HashMap<Integer, String>();
		
		
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String str;
			String testbedName = null;

			while ((str = in.readLine()) != null) {
				StatusTable componentStatusTable = new StatusTable();
				
				str = str.trim();

				if (str.contains("domain:"))
					testbedName = str.split(":")[1].trim();
				
				if(str.startsWith("schema:")){
					String schemaNrAndName = str.split(":")[1].trim();
					Integer schemaNr = null;
					int startOfSchemaName;
					int i = 0;
					while (!Character.isDigit(schemaNrAndName.charAt(i)) && schemaNrAndName.length() > i) i++;
					if(i==schemaNrAndName.length()-1) 
						throw new RuntimeException("Schema definition in OML Stream is wrong");
					if(Character.isDigit(schemaNrAndName.charAt(i+1))){
						schemaNr = Integer.parseInt(schemaNrAndName.substring(i, i+1));
						startOfSchemaName = i+2;
					}else{
						schemaNr = Integer.parseInt(new String(new char[]{schemaNrAndName.charAt(i)}));
						if(schemaNr == 0) continue;
						startOfSchemaName = i+1;
					}
					int endOfSchemaName = schemaNrAndName.lastIndexOf("node");
					
					String schemaName = schemaNrAndName.substring(startOfSchemaName, endOfSchemaName).trim();
					componentSchemaNames.put(schemaNr, schemaName);
				}
					
					

				if (str.length() > 0 && Character.isDigit(str.charAt(0))) {
					
					if (testbedName == null || testbedName == "")
						throw new RuntimeException(
								"The testbed name must be set as domain!");

					String[] strArray = parseLine(str);

					Date lastCheckedDate = null;
					if (strArray[3] != null)
						lastCheckedDate = parseStringToDate(strArray[3]);
					componentStatusTable.setLastCheck(lastCheckedDate);
					componentStatusTable.setId(componentSchemaNames.get(new Integer(strArray[0])));

					if (strArray[2].compareTo("1") == 0) {
						if (isLastCheckedOld(lastCheckedDate)) {
							componentStatusTable.setStatus(StatusTable.UP_AND_LAST_CHECKED_OLD);
						} else
							componentStatusTable.setStatus(StatusTable.UP);
					}

					if (strArray[2].compareTo("0") == 0) {
						componentStatusTable.setStatus(StatusTable.DOWN);
					}

					StatusTable statusTable = new MonitoringManager()
							.getMonitoringDataById(testbedName);

					if (statusTable == null) {
						statusTable = new StatusTable();
						statusTable.setId(testbedName);
						statusTable.addComponent(componentStatusTable);
						statusTable.setLastCheck(componentStatusTable
								.getLastCheck());
						statusTable.setStatus(componentStatusTable.getStatus());
					} else {
						statusTable.addComponent(componentStatusTable);
					}

					statusTable = updateStatusTableState(statusTable);
					new MonitoringManager().pushMonitoringData(statusTable);
				}

			}

			// while ((str=in.readLine()) != null) {
			// StatusTable componentStatusTable = new StatusTable();
			//
			// if(str.contains("domain:"))
			// testbedName=str.split(":")[1].trim();
			//
			// if(str.length()>0 && Character.isDigit(str.charAt(0))){
			// String[] strArray = parseLine(str);
			// Date lastCheckedDate = new Date();
			// if(strArray[3]!=null)
			// lastCheckedDate = parseStringToDate(strArray[3]);
			//
			// componentStatusTable.setLastCheck(lastCheckedDate);
			// componentStatusTable.setId(strArray[1]);
			//
			// if(strArray[2].compareTo("1")==0){
			// if(isLastCheckedOld(lastCheckedDate)){
			// upAndLastCheckedOld = true;
			// componentStatusTable.setStatus(StatusTable.UP_AND_LAST_CHECKED_OLD);
			// }else
			// componentStatusTable.setStatus(StatusTable.UP);
			// oneComponentIsUp=true;
			// if(testbedStatus.compareTo(StatusTable.PARTIALLY)!=0){
			// if (testbedStatus.compareTo(StatusTable.DOWN) == 0){
			// testbedStatus = StatusTable.PARTIALLY;
			// }else
			// testbedStatus=StatusTable.UP;
			// }
			// }
			//
			// if(strArray[2].compareTo("0")==0){
			// componentStatusTable.setStatus(StatusTable.DOWN);
			// testbedStatus = StatusTable.PARTIALLY;
			// }
			//
			// if(lastCheckedDate.before(lastChecked))
			// lastChecked=lastCheckedDate;
			//
			// statusTable.addComponent(componentStatusTable);
			//
			// }
			// if(!oneComponentIsUp)
			// testbedStatus = StatusTable.DOWN;
			//
			// statusTable.setLastCheck(lastChecked);
			// if(testbedStatus.compareTo(StatusTable.UP)==0 &&
			// upAndLastCheckedOld)
			// testbedStatus=StatusTable.UP_AND_LAST_CHECKED_OLD;
			// statusTable.setStatus(testbedStatus);
			// if(testbedName!=null){
			// statusTable.setId(testbedName);
			// new MonitoringManager().pushMonitoringData(statusTable);
			// }
			//
			// }

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			out.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}

	}

	private StatusTable updateStatusTableState(StatusTable statusTable) {

		Date lastChecked = new Date();
		statusTable.setStatus(StatusTable.UNDEFINED);

		Collection<StatusTable> components = statusTable.getComponents();

		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			StatusTable statusTableComponent = (StatusTable) iterator.next();

			if (statusTableComponent.getStatus().compareTo(StatusTable.UP) == 0) {
				if (statusTable.getStatus().compareTo(StatusTable.DOWN) == 0) {
					statusTable.setStatus(StatusTable.PARTIALLY);
				}
				if (statusTable.getStatus().compareTo(StatusTable.UNDEFINED) == 0)
					statusTable.setStatus(StatusTable.UP);
			}

			if (statusTableComponent.getStatus().compareTo(
					StatusTable.UP_AND_LAST_CHECKED_OLD) == 0) {
				if (statusTable.getStatus().compareTo(StatusTable.DOWN) == 0) {
					statusTable.setStatus(StatusTable.PARTIALLY);
				}
				if (statusTable.getStatus().compareTo(StatusTable.UNDEFINED) == 0)
					statusTable.setStatus(StatusTable.UP_AND_LAST_CHECKED_OLD);
				if (statusTable.getStatus().compareTo(StatusTable.UP) == 0)
					statusTable.setStatus(StatusTable.UP_AND_LAST_CHECKED_OLD);
			}

			if (statusTableComponent.getStatus().compareTo(StatusTable.DOWN) == 0) {
				if (statusTable.getStatus().compareTo(StatusTable.UNDEFINED) == 0
						|| statusTable.getStatus().compareTo(StatusTable.DOWN) == 0)
					statusTable.setStatus(StatusTable.DOWN);
				else
					statusTable.setStatus(StatusTable.PARTIALLY);
			}

			if (statusTableComponent.getStatus().compareTo(
					StatusTable.UNDEFINED) == 0) {
				if (statusTable.getStatus().compareTo(StatusTable.UNDEFINED) == 0) {
					statusTable.setStatus(StatusTable.UNDEFINED);
				} else if (statusTable.getStatus().compareTo(StatusTable.DOWN) == 0) {
					statusTable.setStatus(StatusTable.DOWN);
				} else
					statusTable.setStatus(StatusTable.PARTIALLY);
			}

			if (statusTableComponent.getLastCheck().before(lastChecked))
				lastChecked = statusTableComponent.getLastCheck();
		}

		statusTable.setLastCheck(lastChecked);

		return statusTable;
	}

	private boolean isLastCheckedOld(Date lastCheckedDate) {
		Date now = new Date();
		long nowInMilis = now.getTime();
		long lastCheckedDateInMilis = lastCheckedDate.getTime();

		return (nowInMilis - lastCheckedDateInMilis) > timeForOldLastCheckedInMilis;
	}

	private Date parseStringToDate(String dateString) throws ParseException {
		SimpleDateFormat simpleDate = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSSSSz");

		StringBuilder dateStrBuilder = new StringBuilder(dateString);
		dateStrBuilder.deleteCharAt(dateString.lastIndexOf(":"));

		return simpleDate.parse(dateStrBuilder.toString());

	}

	private String[] parseLine(String str) {
		if (str == null)
			return null;

		String[] strArr = str.split("\t");
		String[] response = new String[4];

		response[0] = strArr[1].trim();
		response[1] = strArr[3].trim();
		response[2] = strArr[4].trim();
		response[3] = strArr[5].trim();

		return response;
	}
}
