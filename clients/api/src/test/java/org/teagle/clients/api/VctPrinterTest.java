package org.teagle.clients.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;
import teagle.vct.model.Vct;
import teagle.vct.util.FileUtilities;

public class VctPrinterTest {
	private File vctFilename ;
	private String vctString;
	private Vct vct;

	//@Before
	public void setup() throws FileNotFoundException, IOException {
		// @todo: Very ugly. We need an instance of ModelManager first :/ 
		new LegacyTeagleClient("", "", "http://localhost", "http://localhost:9080/repository/rest");
		
		vctFilename = new File("src/test/resources/vcts/simplevct.xml");
		Assert.assertNotNull(vctFilename);
		this.vctString = FileUtilities.readFileAsString(vctFilename);
		Assert.assertTrue(this.vctString.contains("root"));
		this.vct = LegacyTeagleClient.toVct(vctString);
		Assert.assertNotNull(vct);
	}


	//@Test
	public void testVctParsing() throws IOException {
		String vctConvertedToString = VctPrinter.toStringVct(vct);
		String expected = vct.getCommonName();
		System.out.println(vctConvertedToString);
		Assert.assertTrue(vctConvertedToString.contains(expected));
	}
}
