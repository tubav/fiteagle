package org.fiteagle.core.aaa.x509;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;

import org.junit.Test;

public class X509UtilTest {

	@Test
	public void testGetURN() throws FileNotFoundException, CertificateException {
		InputStream inStream = this.getClass().getResourceAsStream("/homer.pem");
		Collection<? extends Certificate> cert = CertificateFactory.getInstance("X.509").generateCertificates(inStream);
		X509Certificate mycert = (X509Certificate) cert.iterator().next();
		System.out.println( X509Util.getURN(mycert).getSubjectAtDomain());
	}
}
