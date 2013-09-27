package org.fiteagle.interactors.sfa.common;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.zip.Deflater;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.bouncycastle.jcajce.provider.symmetric.AES.OFB;
import org.fiteagle.adapter.common.OpenstackResourceAdapter;
import org.fiteagle.adapter.common.ResourceAdapter;
import org.fiteagle.adapter.common.SSHAccessable;
import org.fiteagle.core.config.InterfaceConfiguration;
import org.fiteagle.interactors.sfa.rspec.SFAv3RspecTranslator;
import org.fiteagle.interactors.sfa.rspec.advertisement.RSpecContents;
import org.fiteagle.interactors.sfa.rspec.ext.ObjectFactory;

public abstract class SFAv3RequestProcessor {

	public AMCode runTimeReturnCode;
	public String outPutString = "";
	private X509Certificate userCertificate;

	protected InterfaceConfiguration interfaceConfig = InterfaceConfiguration
			.getInstance();

	public abstract AMResult processRequest(ListCredentials credentials,
			Object... specificArgs);

	public byte[] compress(String toCompress) {
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



	protected AMCode getReturnCode(GENI_CodeEnum returnCodeEnum) {
		AMCode returnCode = new AMCode();
		returnCode.setGeni_code(returnCodeEnum);
		return returnCode;
	}

//	public RSpecContents parseRSpecContents(String str) {
//		InputStream fromIs = new ByteArrayInputStream(str.getBytes());
//		RSpecContents rSpec = new RSpecContents();
//		JAXBContext jc;
//		try {
//			// jc = JAXBContext.newInstance(
//			// "org.fiteagle.interactors.sfa.rspec" );
//			jc = JAXBContext.newInstance(RSpecContents.class);
//			Unmarshaller u = jc.createUnmarshaller();
//			JAXBElement obj = (JAXBElement) u.unmarshal(fromIs);
//			rSpec = (RSpecContents) obj.getValue();
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
//		return rSpec;
//	}

//




	private void setOutput(String string) {
		this.outPutString = string;

	}

	private void setRuntimeReturnCode(GENI_CodeEnum error) {
		runTimeReturnCode = new AMCode();
		runTimeReturnCode.setGeni_code(error);

	}


	public RSpecContents getAdvertisedRSpec(
			List<ResourceAdapter> resourceAdapters) {
		RSpecContents advertisedRspec = new RSpecContents();
		advertisedRspec.setType("advertisement");

		List<Object> rspecContentElements = advertisedRspec
				.getAnyOrNodeOrLink();
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();

		for (ResourceAdapter resourceAdapter : resourceAdapters) {
			// TODO: if option available set check the resource adapter
			Object resource;
			if (resourceAdapter instanceof OpenstackResourceAdapter)
				resource = translator
				.translateOpenstackResourceAdapterToAdvertisementOpenstackResource((OpenstackResourceAdapter)resourceAdapter);
//			else if (resourceAdapter instanceof SSHAccessable)
//				resource = translator
//						.translateSSHAccesableToAdvertisementNode(resourceAdapter);
			else
				resource = translator
						.translateToFITeagleResource(resourceAdapter);
			rspecContentElements.add(resource);
		}
		return advertisedRspec;
	}

	public RSpecContents getRSpecFromAdapters(
			List<ResourceAdapter> resourceAdapters) {
		RSpecContents advertisedRspec = new RSpecContents();

		List<Object> rspecContentElements = advertisedRspec
				.getAnyOrNodeOrLink();
		SFAv3RspecTranslator translator = new SFAv3RspecTranslator();

		for (ResourceAdapter resourceAdapter : resourceAdapters) {
			Object resource;
//			if (resourceAdapter instanceof SSHAccessable)
//				resource = translator.translateToNode(resourceAdapter);
			if (resourceAdapter instanceof OpenstackResourceAdapter)
				resource = translator.translateToOpenstackResource(resourceAdapter);
			else
				resource = translator
						.translateToFITeagleResource(resourceAdapter);
			rspecContentElements.add(resource);
		}
		return advertisedRspec;
	}

	public X509Certificate getUserCertificate() {
		return userCertificate;
	}

	public void setUserCertificate(X509Certificate userCertificate) {
		this.userCertificate = userCertificate;
	}
	

}
