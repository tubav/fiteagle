package org.fiteagle.interactors.sfa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getFormatedDate(Date expirationTime) {
		 SimpleDateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
			return rfc3339.format(expirationTime);
		}
	public static Date getDateFromString(String expirationTime) throws ParseException{
		 SimpleDateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		 return rfc3339.parse(expirationTime);
	}
}
