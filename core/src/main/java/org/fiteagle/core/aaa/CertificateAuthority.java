package org.fiteagle.core.aaa;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import net.iharder.Base64;

import org.apache.commons.ssl.PKCS8Key;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.fiteagle.core.userdatabase.User;

public class CertificateAuthority {

 
  private X500Name issuer;
 
  private final String caCertPath =  "/org/fiteagle/core/certificates/jettycacert.pem";
  private final String caPrivateKey = "/org/fiteagle/core/certificates/jettyprkey.pem";
  public X509Certificate createCertificate(User newUser) throws Exception{
    X509Certificate caCert = readCACert();
    issuer = new JcaX509CertificateHolder(caCert).getSubject();
    PrivateKey caPrivateKey = readCAPrivateKey();
    ContentSigner contentsigner = new JcaContentSignerBuilder("SHA1WithRSAEncryption").build(caPrivateKey);
    
    X500Name subject = createX500Name(newUser);
    SubjectPublicKeyInfo  subjectsPublicKeyInfo = getPublicKey(newUser);
    X509v3CertificateBuilder ca_gen = new X509v3CertificateBuilder(issuer, new BigInteger( new SecureRandom().generateSeed(256)), new Date(), new Date(System.currentTimeMillis()+ 31500000000L), subject, subjectsPublicKeyInfo); 
    X509CertificateHolder holder = (X509CertificateHolder) ca_gen.build(contentsigner);
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(holder.getEncoded()));
  }

  private SubjectPublicKeyInfo getPublicKey(User newUser) throws Exception {
    KeyDecoder keyDecoder = new KeyDecoder();
    PublicKey key =keyDecoder.decodePublicKey(newUser.getPublicKeys().get(0));
    SubjectPublicKeyInfo subPubInfo = new SubjectPublicKeyInfo((ASN1Sequence) ASN1Sequence.fromByteArray(key.getEncoded()));
    return subPubInfo;
  }

  private X500Name createX500Name(User newUser) {
    X500Principal prince = new X500Principal("CN="+newUser.getUID());
    X500Name x500Name = new X500Name(prince.getName());
    return x500Name;
  }

  //ALL METHODS PUBLIC FOR UNIT TESTING
  public X509Certificate readCACert() throws IOException, CertificateException {

    PEMParser caCertReader = null;
    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(caCertPath))); 
        caCertReader = new PEMParser(reader);
        X509CertificateHolder holder = (X509CertificateHolder) caCertReader.readObject();
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(holder.getEncoded()));
    } finally {
        if (caCertReader != null)
            caCertReader.close();
    }
  }
  
  public PrivateKey readCAPrivateKey() throws IOException, GeneralSecurityException{
    PrivateKey privateKey = null;
    PKCS8Key pkcs8= new PKCS8Key(this.getClass().getResourceAsStream(caPrivateKey), "jetty6".toCharArray());
    byte[] decrypted = pkcs8.getDecryptedBytes();
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec( decrypted );

    privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
    return privateKey;
  }
  
  public void saveCertificate(String name, X509Certificate certificate) throws Exception{
    FileOutputStream fos = new FileOutputStream(name);
    fos.write("-----BEGIN CERTIFICATE-----\n".getBytes());
   
    fos.write(Base64.encodeBytesToBytes(certificate.getEncoded(),0,certificate.getEncoded().length,Base64.DO_BREAK_LINES));
    fos.write("\n-----END CERTIFICATE-----\n".getBytes());
    fos.close();
  }
}
