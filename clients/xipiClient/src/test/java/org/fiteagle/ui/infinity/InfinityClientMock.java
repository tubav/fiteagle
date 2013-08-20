//package org.fiteagle.ui.infinity;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
//import org.fiteagle.ui.infinity.model.InfinityValueID;
//
//public class InfinityClientMock extends InfinityClient {
//
//	public InfinityInfrastructure getInfrastructuresById(Number i) {
//		String input = getMockedInput("/getInfrastructuresByIdResponse.json");
//		InfinityInfrastructure result = null;
//		try {
//			result = this.parser.parseGetInfrastructuresById(input);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//		return result;
//	}
//	
//	public ArrayList<InfinityValueID> searchInfrastructures() {
//		String input = getMockedInput("/searchInfrastructuresResponse.json");
//		ArrayList<InfinityValueID> result = null;
//		try {
//			result = this.parser.parseSearchInfrastructuresResponse(input);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//		return result;
//	}
//
//	private String getMockedInput(String path) {
//		InputStream in = this.getClass().getResourceAsStream(path);
//		return convertStreamToString(in);
//	}
//
//	private static String convertStreamToString(InputStream is) {
//		@SuppressWarnings("resource")
//		Scanner s = new Scanner(is).useDelimiter("\\A");
//		return s.hasNext() ? s.next() : "";
//	}
//}
