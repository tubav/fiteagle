package org.fiteagle.ui.infinity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
//		InputStream in = new FileInputStream(path);
		InputStream in = this.getClass().getResourceAsStream(path);
//		InputStream in = InfinityClientTest.class.getResourceAsStream(path);
		return convertStreamToString(in);
	}

	private static String convertStreamToString(InputStream is) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
		System.out.println("S IN CLIENT IS: "+s);
		return s.hasNext() ? s.next() : "";
	}
}
