package org.fiteagle.ui.infinity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.fiteagle.ui.infinity.model.InfinityArrayList;
import org.fiteagle.ui.infinity.model.InfinityInfrastructure;
import org.fiteagle.ui.infinity.model.InfinityValueID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfinityParserTest {

	private InfinityParser parser;

	@Before
	public void setup() {
		this.parser = new InfinityParser();
	}

	@Test
	public void testGetTechnicalComponentsParser() throws IOException {
		String input = getMockedInput("/getTechnicalComponentsResponse.json");
		ArrayList<InfinityValueID> result = this.parser
				.parseGetTechnicalComponentsResponse(input);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testGetInfrastructuresByIdParser() throws IOException {
		String input = getMockedInput("/getInfrastructuresByIdResponse.json");
		InfinityInfrastructure result = this.parser
				.parseGetInfrastructuresById(input);
		Assert.assertNotNull(result);
	}

	@Test
	public void testWritingGetInfrastructuresByIdResponse() throws IOException {
		String input = getMockedInput("/getInfrastructuresByIdResponse.json");
		InfinityInfrastructure result = this.parser
				.parseGetInfrastructuresById(input);
		this.parser.write(new NullOutputStream(), result);
	}

	@Test
	public void testGetComponentDetailResponseParser() throws IOException {
		String input = getMockedInput("/getComponentDetailResponse.json");
		ArrayList<InfinityArrayList> result = this.parser
				.parseGetComponentDetailResponse(input);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void testSearchInfrastructuresResponseParser() throws IOException {
		String input = getMockedInput("/searchInfrastructuresResponse.json");
		ArrayList<InfinityValueID> result = this.parser
				.parseSearchInfrastructuresResponse(input);
		Assert.assertFalse(result.isEmpty());
	}

	private String getMockedInput(String path) {
		InputStream in = this.getClass().getResourceAsStream(path);
		return convertStreamToString(in);
	}

	private static String convertStreamToString(InputStream is) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	private class NullOutputStream extends OutputStream {
		@Override
		public void write(int b) throws IOException {
		}
	}
}
