package org.fiteagle.adapter.openstackvmadapter.client;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DeleteMe {
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println("Calendar Object: "+calendar.toString());
		System.out.println("Calendar Object in milis: "+calendar.getTimeInMillis());
		
		long timeInmilis = calendar.getTimeInMillis();
		String timeInMilisString = String.valueOf(timeInmilis);
		
		//other side
		
		long timesInMiliLong = Long.parseLong(timeInMilisString);
		
		GregorianCalendar gregCal = new GregorianCalendar();
		gregCal.setTime(new Date(timesInMiliLong));
		XMLGregorianCalendar xmlGregCal = null;
		try {
			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("XMLGREGCAL: "+xmlGregCal);
		System.out.println("XMLGREGCAL time in milis: "+xmlGregCal.toGregorianCalendar().getTimeInMillis());
		
		
	}

}
