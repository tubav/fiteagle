package org.fiteagle.delivery.xmlrpc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FITeagleUtils {

	private FITeagleUtils() {
	}

	public static String getFileAsString(String filePath)
			throws java.io.IOException {
		final InputStream resourceAsStream = getFileAsInputStream(filePath);
		final StringBuffer fileData = new StringBuffer(1024);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				resourceAsStream));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	public static InputStream getFileAsInputStream(String filePath)
			throws IOException {
		final InputStream resourceAsStream = FITeagleUtils.class
				.getResourceAsStream(filePath);

		if (null == resourceAsStream)
			throw new IOException("File not found in classpath: " + filePath);

		return resourceAsStream;
	}
}