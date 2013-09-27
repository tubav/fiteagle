package org.fiteagle.interactors.sfa.register;

import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.easymock.EasyMock;
import org.fiteagle.core.aaa.KeyStoreManagement;
import org.fiteagle.core.aaa.x509.X509Util.GenerateCertificateException;
import org.fiteagle.core.groupmanagement.Group;
import org.fiteagle.core.groupmanagement.GroupDBManager;
import org.fiteagle.core.util.URN;
import org.fiteagle.interactors.sfa.getSelfCredential.jaxbClasses.SignedCredential;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterRequestProcessorTest {

	RegisterRequestProcessor proc = null;
	private HashMap<String, Object> hashMap;
	private String urnString;

	@Before
	public void setUp() throws Exception {
	
		KeyStoreManagement keyStoreManagement = EasyMock.createMock(KeyStoreManagement.class);
		GroupDBManager groupDBManager = EasyMock.createMock(GroupDBManager.class);
		groupDBManager.addGroup(EasyMock.anyObject(Group.class));
		EasyMock.expectLastCall();
		Group g = new Group("grmps@wall3.test.ibbt.be", "dnehls@something");
		EasyMock.expect(groupDBManager.getGroup(EasyMock.anyObject(String.class))).andReturn(g);
		
		EasyMock.replay(groupDBManager);
		
		X509Certificate dummyCert = EasyMock.createMock(X509Certificate.class);
		EasyMock.expect(dummyCert.getEncoded()).andReturn(new byte[]{});
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(dummyCert);
		EasyMock.expect(keyStoreManagement.getResourceCertificate(EasyMock.anyObject(String.class))).andReturn(dummyCert);
		EasyMock.replay(keyStoreManagement);
		proc = new RegisterRequestProcessor(keyStoreManagement,groupDBManager);
		hashMap = new HashMap<String, Object>();
		hashMap.put("type", "Slice");
		urnString = "urn:publicid:IDN+wall3.test.ibbt.be+slice+grmps";
		hashMap.put("urn", urnString);
		hashMap.put(
				"credential",
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
						+ "<signed-credential xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\">"
						+ "<credential id=\"b077930c-4b97-47af-8c2b-e60663ea2532\" xml:id=\"b077930c-4b97-47af-8c2b-e60663ea2532\">"
						+ "<type>privilege</type>"
						+ "<owner_gid>"
						+ "MIIDyzCCAzSgAwIBAgICA38wDQYJKoZIhvcNAQEEBQAwgbkxCzAJBgNVBAYTAkJFMQwwCgYDVQQI"
						+ "EwNPVkwxDjAMBgNVBAcTBUdoZW50MR4wHAYDVQQKExVVR2VudC1JbnRlYy1JQkNOL0lCQlQxHjAc"
						+ "BgNVBAsTFUNlcnRpZmljYXRlIEF1dGhvcml0eTEgMB4GA1UEAxMXYm9zcy53YWxsMy50ZXN0Lmli"
						+ "YnQuYmUxKjAoBgkqhkiG9w0BCQEWG3Z3YWxsLW9wc0BhdGxhbnRpcy51Z2VudC5iZTAeFw0xMzA1"
						+ "MjkxMDE1MDdaFw0xNDA1MjkxMDE1MDdaMIGvMQswCQYDVQQGEwJCRTEMMAoGA1UECBMDT1ZMMR4w"
						+ "HAYDVQQKExVVR2VudC1JbnRlYy1JQkNOL0lCQlQxGTAXBgNVBAsTEHdhbGwzZ2VuaS5kbmVobHMx"
						+ "LTArBgNVBAMTJGY2Mzc3YjhlLWM3OWQtMTFlMi1hMmM3LTAwNTA1NmJjNDc5ZjEoMCYGCSqGSIb3"
						+ "DQEJARYZZG5laGxzQHdhbGwzLnRlc3QuaWJidC5iZTCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkC"
						+ "gYEAssY+UjARwMKYpt+3ULpE/4cEogiuT7hm3a4CyqHHNw5iHVwXXAhqOxSwC1D8rcc90MjcqmYw"
						+ "gzu5Eh8o30EujPB68Sl3egfXlMEim5+xTgLR+8Rj4jDmOYtLlVxug1PIXiOVV1LvgtoNneWEN8yz"
						+ "2NRW2dYB7ltRquC4iXr35uECAwEAAaOB6TCB5jAMBgNVHRMBAf8EAjAAMB0GA1UdDgQWBBQB9Kh6"
						+ "vec1JaDLIEjMP0iN8eV21DBVBgNVHREETjBMhi91cm46cHVibGljaWQ6SUROK3dhbGwzLnRlc3Qu"
						+ "aWJidC5iZSt1c2VyK2RuZWhsc4EZZG5laGxzQHdhbGwzLnRlc3QuaWJidC5iZTBgBggrBgEFBQcB"
						+ "AQRUMFIwUAYUaYPMk4ComMyox72xp4CAqq7XihuGOGh0dHBzOi8vd3d3LndhbGwzLnRlc3QuaWJi"
						+ "dC5iZToxMjM2OS9wcm90b2dlbmkveG1scnBjL3NhMA0GCSqGSIb3DQEBBAUAA4GBAFTjSheIbvsy"
						+ "DCCpj8YdVo2ciZtaHnRc1QVA3jFL0O8Tfx72PtjCIK27v2tyNoCCOudLFYIm7vCnCF2rbnZnh7+I"
						+ "1b3nUwsbKNJRMiJzU/Xip/0PoNrLTztHWEcO4wyMdb6xwDikSGhXA93MFNzAHnkoSrCp5jAwVKHd"
						+ "bXB4y2d9"
						+ "</owner_gid>"
						+ "<owner_urn>urn:publicid:IDN+wall3:test:ibbt+user+dnehls</owner_urn>"
						+ "<target_urn>urn:publicid:IDN+fiteagle-fuseco.fokus.fraunhofer.de+authority+sa</target_urn>"
						+ "<target_gid>"
						+ "MIID5TCCA06gAwIBAgIBEDANBgkqhkiG9w0BAQUFADCBijELMAkGA1UEBhMCREUxDzANBgNVBAgM"
						+ "BkJlcmxpbjEPMA0GA1UEBwwGQmVybGluMRkwFwYDVQQKDBBGcmF1bmhvZmVyIEZPS1VTMQ0wCwYD"
						+ "VQQLDAROR05JMS8wLQYDVQQDDCZjYS5maXRlYWdsZS1mdXNlY28uZm9rdXMuZnJhdW5ob2Zlci5k"
						+ "ZTAeFw0xMzA2MDUxMzU2MTVaFw0xNDA2MDUxMzU2MTVaMHkxCzAJBgNVBAYTAkRFMQ8wDQYDVQQI"
						+ "DAZCZXJsaW4xGTAXBgNVBAoMEEZyYXVuaG9mZXIgRk9LVVMxDTALBgNVBAsMBE5HTkkxLzAtBgNV"
						+ "BAMMJnNhLmZpdGVhZ2xlLWZ1c2Vjby5mb2t1cy5mcmF1bmhvZmVyLmRlMIGfMA0GCSqGSIb3DQEB"
						+ "AQUAA4GNADCBiQKBgQDK/pW+TituO/99j7/QAtvkumjBv5WgB47WasPlscy+RjLvbclgfNSjfvJ4"
						+ "affzS3aEhyVhxJCxqF5N12dy6E1m/75hKccPgBFHe2C6UcSILN1UK6w3u4gmohlLIeiAGR8HcSdu"
						+ "oJ1rnS9H6MUHtX6leVzwsjjxj5Kth8iv1ZJ3VQIDAQABo4IBaTCCAWUwDwYDVR0TAQH/BAUwAwEB"
						+ "/zAsBglghkgBhvhCAQ0EHxYdT3BlblNTTCBHZW5lcmF0ZWQgQ2VydGlmaWNhdGUwHQYDVR0OBBYE"
						+ "FOcb4ZyRC57cXrlYtX2kzQ2evFBCMIGpBgNVHSMEgaEwgZ6hgZCkgY0wgYoxCzAJBgNVBAYTAkRF"
						+ "MQ8wDQYDVQQIDAZCZXJsaW4xDzANBgNVBAcMBkJlcmxpbjEZMBcGA1UECgwQRnJhdW5ob2ZlciBG"
						+ "T0tVUzENMAsGA1UECwwETkdOSTEvMC0GA1UEAwwmY2EuZml0ZWFnbGUtZnVzZWNvLmZva3VzLmZy"
						+ "YXVuaG9mZXIuZGWCCQCbHKdifYh3XzBMBgNVHREERTBDhkF1cm46cHVibGljaWQ6SUROK2ZpdGVh"
						+ "Z2xlLWZ1c2Vjby5mb2t1cy5mcmF1bmhvZmVyLmRlK2F1dGhvcml0eStzYTALBgNVHQ8EBAMCBeAw"
						+ "DQYJKoZIhvcNAQEFBQADgYEAh+ToI9ce0dfEOCrWV4Ak6rE2rL71DN5vCbWi9N96x1KgUa5P2/bi"
						+ "eWe3YlCXE4X0ilIWPaubKiYKkm5axcfA9K3YJ3v/2o9JO1y2xM41PJ523vtiRRUTNeSbNho8T8sI"
						+ "3bNe60n+7XGvrmfuazNJP3wJHoeGkZubff+rDMrzT5s=</target_gid>"
						+ "<expires>2013-06-13T12:41:17.613+02:00</expires>"
						+ "<privileges>"
						+ "<privilege>"
						+ "<name>*</name>"
						+ "<can_delegate>false</can_delegate>"
						+ "</privilege>"
						+ "</privileges>"
						+ "</credential>"
						+ "<signatures>"
						+ "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">"
						+ "<SignedInfo>"
						+ "<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></CanonicalizationMethod>"
						+ "<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></SignatureMethod>"
						+ "<Reference URI=\"#b077930c-4b97-47af-8c2b-e60663ea2532\">"
						+ "<Transforms>"
						+ "<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></Transform>"
						+ "</Transforms>"
						+ "<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></DigestMethod>"
						+ "<DigestValue>0ItQKOMvRqy8Nrl4zKPwNOw0N4g=</DigestValue>"
						+ "</Reference>"
						+ "</SignedInfo>"
						+ "<SignatureValue>"
						+ "n+NXwlMFJdghnlWVyD41g0NSkKL5ri8hfEOJYxvRkhjY9nKkpV1Yp5CtgNRA+993Ra2fMxolQokO"
						+ "z7dC7aZZ/RV+fDeSid+VS63Gp3Ct02cXnnW+NBUlFXZAiJrYzGUO6aNugyZt3htKa/JZJyeOxOJq"
						+ "cGoBBVs6MghEw/pIxJo="
						+ "</SignatureValue>"
						+ "<KeyInfo>"
						+ "<X509Data>"
						+ "<X509Certificate>"
						+ "MIIC9zCCAmCgAwIBAgIJAJscp2J9iHdfMA0GCSqGSIb3DQEBBQUAMIGKMQswCQYDVQQGEwJERTEP"
						+ "MA0GA1UECAwGQmVybGluMQ8wDQYDVQQHDAZCZXJsaW4xGTAXBgNVBAoMEEZyYXVuaG9mZXIgRk9L"
						+ "VVMxDTALBgNVBAsMBE5HTkkxLzAtBgNVBAMMJmNhLmZpdGVhZ2xlLWZ1c2Vjby5mb2t1cy5mcmF1"
						+ "bmhvZmVyLmRlMB4XDTEzMDYwNTEwMzMxM1oXDTIzMDYwMzEwMzMxM1owgYoxCzAJBgNVBAYTAkRF"
						+ "MQ8wDQYDVQQIDAZCZXJsaW4xDzANBgNVBAcMBkJlcmxpbjEZMBcGA1UECgwQRnJhdW5ob2ZlciBG"
						+ "T0tVUzENMAsGA1UECwwETkdOSTEvMC0GA1UEAwwmY2EuZml0ZWFnbGUtZnVzZWNvLmZva3VzLmZy"
						+ "YXVuaG9mZXIuZGUwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAPDGvuABELKZ5bEC7GiIHuES"
						+ "JP+ALvLbymq43IajduyPEY5ELMEUW2jE9Eg6pE7uYL8NKYDS+mEju//eaFa1uiioWOpOjxKlbCCp"
						+ "hN66Oi2Pkd8l0a/nSjNEcMvSRE0HJheLcqrGm7CXj0cWufKx1AHbpGWfFiSHGOOY+BnwJ/zpAgMB"
						+ "AAGjYzBhMA8GA1UdEwEB/wQFMAMBAf8wTgYDVR0RBEcwRYZDdXJuOnB1YmxpY2lkOklETitmaXRl"
						+ "YWdsZS1mdXNlY28uZm9rdXMuZnJhdW5ob2Zlci5kZSthdXRob3JpdHkrcm9vdDANBgkqhkiG9w0B"
						+ "AQUFAAOBgQAcgKH2+MyFkfo8zJOmyTYL8PjfSc9WHSmnO+wFB+0T0Q0qd1Bf1NLO3Bh5cMMbfmp7"
						+ "ojjBnYvPDoxJ+uFnYtuNDhilgZXXs5Tz+XpB5QM89gBrTDPXeuFMxDaiVFas0V07IbTFMeTy9Lbf"
						+ "W4Cgnsdv/1WETiMTac8r2gsEDOH0yw=="
						+ "</X509Certificate>"
						+ "</X509Data>"
						+ "<KeyValue>"
						+ "<RSAKeyValue>"
						+ "<Modulus>"
						+ "8Ma+4AEQspnlsQLsaIge4RIk/4Au8tvKarjchqN27I8RjkQswRRbaMT0SDqkTu5gvw0pgNL6YSO7"
						+ "/95oVrW6KKhY6k6PEqVsIKmE3ro6LY+R3yXRr+dKM0Rwy9JETQcmF4tyqsabsJePRxa58rHUAduk"
						+ "ZZ8WJIcY45j4GfAn/Ok=" + "</Modulus>"
						+ "<Exponent>AQAB</Exponent>" + "</RSAKeyValue>"
						+ "</KeyValue>" + "</KeyInfo>" + "</Signature>"
						+ "</signatures>" + "</signed-credential>");
	}

	@Test
	public void registerTest() {

		HashMap<String, Object> result = proc.register(hashMap);
		Assert.assertNotNull(result);

	}

	@Test
	public void isSliceType() {
		Assert.assertTrue(proc.isSliceType("Slice"));
	}

	@Test
	public void getAndTestType() {
		String type = proc.getType(hashMap);
		Assert.assertTrue(proc.isSliceType(type));
	}

	@Test
	public void getUrnTest() {
		URN urn = proc.getUrn(hashMap);
	}

	@Test
	public void getCredentialTest() {
		String credentials = proc.getCredential(hashMap);
		Assert.assertNotNull(credentials);
	}

	@Test
	public void buildCredentialTest() throws JAXBException {
		String credential = proc.getCredential(hashMap);
		SignedCredential sg = proc.buildCredential(credential);
		sg.getCredential().getOwnerURN();
	}

	@After
	public void deleteTestGroup() {
		GroupDBManager.getInstance().deleteGroup(
				new URN(urnString).getSubjectAtDomain());
	}
}
