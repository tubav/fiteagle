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

import org.fiteagle.ui.infinity.model.InfinityArrayList;
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
		InputStream fixedIn = fixXIPIEncoding(in);
		return convertStreamToString(fixedIn);
	}

	@Override
	public ArrayList<InfinityValueID> getTechnicalComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InfinityArrayList> getComponentDetail(
			String infrastructureId, String componentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
