package org.fiteagle.core.aaa;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import net.iharder.Base64;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.fiteagle.core.config.FiteaglePreferences;
import org.fiteagle.core.config.FiteaglePreferencesXML;
import org.fiteagle.core.userdatabase.User;

public class CertificateAuthority {

 
  private X500Name issuer;
  FiteaglePreferences preferences = null;
  private KeyStoreManagement keyStoreManagement = new KeyStoreManagement();
  
  public X509Certificate createCertificate(User newUser) throws Exception{
    X509Certificate caCert = keyStoreManagement.getCACert();
    issuer = new JcaX509CertificateHolder(caCert).getSubject();
    PrivateKey caPrivateKey = keyStoreManagement.getCAPrivateKey();
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

 
 
  
  public void saveCertificate(String name, X509Certificate certificate) throws Exception{
    FileOutputStream fos = new FileOutputStream(name);
    fos.write("-----BEGIN CERTIFICATE-----\n".getBytes());
   
    fos.write(Base64.encodeBytesToBytes(certificate.getEncoded(),0,certificate.getEncoded().length,Base64.DO_BREAK_LINES));
    fos.write("\n-----END CERTIFICATE-----\n".getBytes());
    fos.close();
  }
  
 
  


  


}