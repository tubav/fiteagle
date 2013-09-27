package org.fiteagle.interactors.sfa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getFormatedDate(Date expirationTime) {
		 SimpleDateFormat rfc3339 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
			return rfc3339.format(expirationTime);
		}
}
