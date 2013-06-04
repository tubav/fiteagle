package org.fiteagle.core.aaa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import net.iharder.Base64;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.userdatabase.User;
import org.fiteagle.core.userdatabase.UserDB.DatabaseException;
import org.fiteagle.core.userdatabase.UserDB.RecordNotFoundException;
import org.fiteagle.core.userdatabase.UserDBManager;

public class CertificateAuthority {
  
  private UserDBManager userDBManager;
  private static CertificateAuthority CA = null;
  
  public static CertificateAuthority getInstance() {
    if (CA == null)
      CA = new CertificateAuthority();
    
    return CA;
  }
  
  private CertificateAuthority() {
    try {
      userDBManager = UserDBManager.getInstance();
    } catch (DatabaseException e) {
      throw e;
    }
  }
  
  private KeyStoreManagement keyStoreManagement = KeyStoreManagement.getInstance();
  
  public X509Certificate createCertificate(User newUser, PublicKey publicKey) throws Exception {
    X509Certificate caCert = keyStoreManagement.getCACert();
    X500Name issuer = new JcaX509CertificateHolder(caCert).getSubject();
    PrivateKey caPrivateKey = keyStoreManagement.getCAPrivateKey();
    ContentSigner contentsigner = new JcaContentSignerBuilder("SHA1WithRSAEncryption").build(caPrivateKey);
    
    X500Name subject = createX500Name(newUser);
    SubjectPublicKeyInfo subjectsPublicKeyInfo = getPublicKey(publicKey);
    X509v3CertificateBuilder ca_gen = new X509v3CertificateBuilder(issuer, new BigInteger(
        new SecureRandom().generateSeed(256)), new Date(), new Date(System.currentTimeMillis() + 31500000000L),
        subject, subjectsPublicKeyInfo);
    BasicConstraints ca_constraint = new BasicConstraints(false);
    ca_gen.addExtension(X509Extension.basicConstraints, true, ca_constraint);
    GeneralNames subjectAltName = new GeneralNames(new GeneralName(GeneralName.uniformResourceIdentifier,
        userDBManager.getOwnerURN(newUser)));
    
    X509Extension extension = new X509Extension(false, new DEROctetString(subjectAltName));
    ca_gen.addExtension(X509Extension.subjectAlternativeName, false, extension.getParsedValue());
    X509CertificateHolder holder = (X509CertificateHolder) ca_gen.build(contentsigner);
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(holder.getEncoded()));
  }
  
  public String getServerURN() throws CertificateParsingException {
    X509Certificate caCert = getServerCertificate();
    Collection<List<?>> alternativeNames = caCert.getSubjectAlternativeNames();
    Iterator<?> iter =  alternativeNames.iterator();
    String urn = "";
    while(iter.hasNext()){
      List<?> altName = (List<?>) iter.next();
      if (altName.get(0).equals(Integer.valueOf(6))) {
        urn = (String) altName.get(1);
      }
    }
    return urn;
  }
  
  public void saveCertificate(String name, X509Certificate certificate) throws Exception {
    FileOutputStream fos = new FileOutputStream(name);
    fos.write(getCertficateEncoded(certificate).getBytes());
    fos.close();
  }
  
  public X509Certificate getServerCertificate() {
    try {
      return keyStoreManagement.getCACert();
    } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
      throw new CertificateNotFoundException();
    }
  }
  
  public String getCertficateEncoded(X509Certificate cert) throws Exception {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    bout.write("-----BEGIN CERTIFICATE-----\n".getBytes());
    bout.write(Base64.encodeBytesToBytes(cert.getEncoded(), 0, cert.getEncoded().length, Base64.DO_BREAK_LINES));
    bout.write("\n-----END CERTIFICATE-----\n".getBytes());
    String encodedCert = new String(bout.toByteArray());
    bout.close();
    return encodedCert;
  }
  
  public String getCertificateBodyEncoded(X509Certificate cert) throws Exception {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    bout.write(Base64.encodeBytesToBytes(cert.getEncoded(), 0, cert.getEncoded().length, Base64.DO_BREAK_LINES));
    String encodedCert = new String(bout.toByteArray());
    bout.close();
    return encodedCert;
  }
  
  public String getUserCertificateAsString(String certString) {
    CertificateFactory cf = getCertifcateFactory();
    
    X509Certificate userCert = getX509Certificate(cf, certString);
    
    User user = getUserFromCert(userCert);
    String alias = user.getUID();
    X509Certificate[] storedCertificate = getStoredCertificate(alias);
    if (storedCertificate == null) {
      try {
        
        keyStoreManagement.storeCertificate(alias, userCert);
        storedCertificate = new X509Certificate[]{userCert};
      } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    try {
      return getCertificateBodyEncoded(storedCertificate[0]);
    } catch (Exception e) {
      throw new EncodeCertificateException();
    }
  }
  
  private X509Certificate[] getStoredCertificate(String alias) {
    return keyStoreManagement.getStoredCertificate(alias);
  }
  
  private X509Certificate getX509Certificate(CertificateFactory cf, String certString) {
    InputStream in = new ByteArrayInputStream(certString.getBytes());
    try {
      return (X509Certificate) cf.generateCertificate(in);
    } catch (Exception e) {
      throw new GenerateCertificateException();
    }
  }
  
  private User getUserFromCert(X509Certificate userCert) {
    
    return userDBManager.getUserFromCert(userCert);
    
  }
  
  private CertificateFactory getCertifcateFactory() {
    try {
      return CertificateFactory.getInstance("X.509");
    } catch (CertificateException e) {
      throw new CertificateFactoryNotCreatedException();
    }
  }
  
  private SubjectPublicKeyInfo getPublicKey(PublicKey key) throws Exception {
    
    SubjectPublicKeyInfo subPubInfo = new SubjectPublicKeyInfo((ASN1Sequence) ASN1Sequence.fromByteArray(key
        .getEncoded()));
    return subPubInfo;
  }
  
  private X500Name createX500Name(User newUser) {
    X500Principal prince = new X500Principal("CN=" + newUser.getUID());
    X500Name x500Name = new X500Name(prince.getName());
    return x500Name;
  }
  
  public class CertificateFactoryNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
  }
  
  public class GenerateCertificateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  }
  
  public class EncodeCertificateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  }
  
  public class CertificateNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
  }
  
}
