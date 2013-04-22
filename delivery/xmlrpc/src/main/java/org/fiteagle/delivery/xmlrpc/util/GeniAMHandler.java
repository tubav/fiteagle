package org.fiteagle.delivery.xmlrpc.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.fiteagle.interactors.sfa.ISFA;
import org.fiteagle.interactors.sfa.SFAInteractor_v3;
import org.fiteagle.interactors.sfa.common.AMCode;
import org.fiteagle.interactors.sfa.common.AMResult;
import org.fiteagle.interactors.sfa.common.Credentials;
import org.fiteagle.interactors.sfa.common.GENI_CodeEnum;
import org.fiteagle.interactors.sfa.common.GeniAvailableOption;
import org.fiteagle.interactors.sfa.common.Geni_RSpec_Version;
import org.fiteagle.interactors.sfa.common.ListCredentials;
import org.fiteagle.interactors.sfa.listresources.ListResourceOptions;
import org.slf4j.Logger;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcStruct;

public class GeniAMHandler extends SFAHandler {

	ISFA interactor;

	private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	public GeniAMHandler(SFAInteractor_v3 sfaInteractor_v3) {
		setInteractor(sfaInteractor_v3);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object invoke(String methodName, List parameters) throws Throwable {
		
	Object response = null;

		try {
			Method knownMethod = getMethod(methodName);
			AMResult result =(AMResult) getMethodCallResult(knownMethod, parameters);
			response = createResponse(result);
		} catch (ParsingException e) {
			response = createErrorResponse(e);
		}
		return response;

	}

	private Object createErrorResponse(ParsingException e) throws IOException {
		AMResult errorResult = getErrorResult(e);
		Object errorResponse = introspect(errorResult);
		return errorResponse;
	}

	private AMResult getErrorResult(ParsingException e) {
		AMResult result = new AMResult();
		result.setOutput(e.getMessage());
		AMCode errorCode = getErrorCode(e);
		result.setCode(errorCode);
		return result;
		
	}

	private AMCode getErrorCode(ParsingException e) {
		AMCode code = new AMCode();
		code.setGeni_code(e.getErrorCode());
		return code;
	}

	private Object createResponse(AMResult result) {
		Object response = new HashMap<>();
		try {
			response = introspect(result);
		} catch (IOException ioException) {
			log.error(ioException.getMessage(),ioException);
		}
		return response;
	}



	protected void xmlStructToObject(Object from, Object to) {
		if (to.getClass().isAssignableFrom(ListResourceOptions.class)) {
			XmlRpcStruct listResourceOptionsStruct = (XmlRpcStruct) from;
			ListResourceOptions listResourceOptions = (ListResourceOptions) to;
			listResourceOptions.setGeni_available(new GeniAvailableOption(
					listResourceOptionsStruct.getBoolean("geni_available")));
			listResourceOptions.setGeni_compressed(new org.fiteagle.interactors.sfa.common.GeniCompressedOption(
					listResourceOptionsStruct.getBoolean("geni_compressed")));

			XmlRpcStruct geni_rspec_version_struct = listResourceOptionsStruct
					.getStruct("geni_rspec_version");
			if (geni_rspec_version_struct != null) {
				String type = geni_rspec_version_struct.getString("type");
				String version = geni_rspec_version_struct.getString("version");
				Geni_RSpec_Version geni_RSpec_Version = new Geni_RSpec_Version();
				geni_RSpec_Version.setType(type);
				geni_RSpec_Version.setVersion(version);

				listResourceOptions.setGeni_rspec_version(geni_RSpec_Version);

			} else {
				// TODO error handling throw exception => set corresponding
				// error code of response
			}

		}
		if (to.getClass().isAssignableFrom(ListCredentials.class)) {
			try {
				XmlRpcArray listCredentialsArray = (XmlRpcArray) from;
				ListCredentials listCredentials = (ListCredentials) to;
				if (listCredentialsArray.size() > 0) {
					for (int i = 0; i < listCredentialsArray.size(); i++) {
						XmlRpcStruct credentialsStruct = (XmlRpcStruct) listCredentialsArray
								.get(i);
						Credentials credentials = new Credentials();
						if (credentialsStruct.getString("geni_type") != null) {
							credentials.setGeni_type(credentialsStruct
									.getString("geni_type"));
						} else {
							throw new CredentialsNotValid();
						}
						if (credentialsStruct.getString("geni_version") != null) {
							credentials.setGeni_version(credentialsStruct
									.getString("geni_version"));
						} else {
							throw new CredentialsNotValid();
						}
						if (credentialsStruct.getString("geni_value") != null) {
							credentials.setGeni_value(credentialsStruct
									.getString("geni_value"));
						} else {
							throw new CredentialsNotValid();
						}

						listCredentials.addCredentials(credentials);

					}
				}
			} catch (ClassCastException e) {
				throw new CredentialsNotValid();
			}
		}

	}


	private class ParsingException extends RuntimeException {
		private GENI_CodeEnum errorCode = GENI_CodeEnum.ERROR;
		private String errorMessage = "Error";
		private static final long serialVersionUID = 1L;
		public void setErrorCode(GENI_CodeEnum errorCode) {
			this.errorCode = errorCode;
		}
		
		public GENI_CodeEnum getErrorCode() {
			return errorCode;
		}
		
		public String getMessage(){
			return this.errorMessage;
		}
		
		public void setMessage(String message){
			this.errorMessage = message;
		}
	}

	private class CredentialsNotValid extends ParsingException {

		private static final long serialVersionUID = 1L;
		public CredentialsNotValid() {
			setErrorCode(GENI_CodeEnum.BADARGS);
			setMessage("Credentials not Valid");
		}


	}}


