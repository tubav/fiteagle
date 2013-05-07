package org.fiteagle.interactors.sfa.common;

import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;


public abstract class SFAv3RequestProcessor {

	public abstract AMResult processRequest(ListCredentials credentials,
			Object... specificArgs);
	
	public  byte[] compress(String toCompress) {
		String inputString = toCompress;
		byte[] input;
		int compressedDataLength = 0;
		byte[] output = null;
		
		try {
			input = inputString.getBytes("UTF-8");
			// Compress the bytes
			output = new byte[input.length];
			Deflater compresser = new Deflater();
			compresser.setInput(input);
			compresser.finish();
			compressedDataLength = compresser.deflate(output);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}

	
}
