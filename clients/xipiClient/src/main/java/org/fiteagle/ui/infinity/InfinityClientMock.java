package org.fiteagle.ui.infinity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;

public class InfinityClientMock extends InfinityClient {

	public InfinityInfrastructure getInfrastructuresById(Number i) {
		String input;
		try {
			input = getMockedInput("/getInfrastructuresByIdResponse.json");
		} catch (FileNotFoundException e1) {
			throw new RuntimeException(e1.getMessage());
		}
		InfinityInfrastructure result = null;
		try {
			result = this.parser.parseGetInfrastructuresById(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}
	
	public ArrayList<InfinityValueID> searchInfrastructures() {
		String input;
		try {
			input = getMockedInput("/searchInfrastructuresResponse.json");
		} catch (FileNotFoundException e1) {
			throw new RuntimeException(e1.getMessage());
		}
		ArrayList<InfinityValueID> result = null;
		try {
			result = this.parser.parseSearchInfrastructuresResponse(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	private String getMockedInput(String path) throws FileNotFoundException {
		InputStream in = this.getClass().getResourceAsStream(path);
		InputStream fixedIn = fixEncoding(in);
		return convertStreamToString(fixedIn);
	}

	private InputStream fixEncoding(InputStream in) {
		
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int len;
	    try {
			while ((len = in.read(buffer)) > -1 ) {
			    out.write(buffer, 0, len);
			}
		} catch (IOException e2) {
			throw new RuntimeException(e2.getMessage());
		}
	    try {
			out.flush();
		} catch (IOException e1) {
			throw new RuntimeException(e1.getMessage());
		}
	    
	    byte[] utf8 = null;
		try {
			utf8 = new String(out.toByteArray(), "ISO-8859-1").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return new ByteArrayInputStream(utf8);
	}

	private static String convertStreamToString(InputStream is) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
