package org.fiteagle.core.aaa;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import net.iharder.Base64;

import org.fiteagle.core.userdatabase.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyManagement {
  
  private byte[] bytes;
  private int pos;
  
  Logger log = LoggerFactory.getLogger(getClass());
  
  public PublicKey decodePublicKey(String keyLine) throws Exception {
    bytes = null;
    pos = 0;
    // look for the Base64 encoded part of the line to decode
    // both ssh-rsa and ssh-dss begin with "AAAA" due to the length bytes
    for (String part : keyLine.split(" ")) {
      if (part.startsWith("AAAA")) {
        bytes = Base64.decode(part);
        break;
      }
    }
    if (bytes == null) {
      throw new IllegalArgumentException("no Base64 part to decode");
    }
    
    String type = decodeType();
    if (type.equals("ssh-rsa")) {
      BigInteger e = decodeBigInt();
      BigInteger m = decodeBigInt();
      RSAPublicKeySpec spec = new RSAPublicKeySpec(m, e);
      return KeyFactory.getInstance("RSA").generatePublic(spec);
    } else if (type.equals("ssh-dss")) {
      BigInteger p = decodeBigInt();
      BigInteger q = decodeBigInt();
      BigInteger g = decodeBigInt();
      BigInteger y = decodeBigInt();
      DSAPublicKeySpec spec = new DSAPublicKeySpec(y, p, q, g);
      return KeyFactory.getInstance("DSA").generatePublic(spec);
    } else {
      throw new IllegalArgumentException("unknown type " + type);
    }
  }
  
  public String encodePublicKey(PublicKey pubKey) throws IOException {
    String publicKeyEncoded;
    if (pubKey.getAlgorithm().equals("RSA")) {
      RSAPublicKey rsaPublicKey = (RSAPublicKey) pubKey;
      ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(byteOs);
      dos.writeInt("ssh-rsa".getBytes().length);
      dos.write("ssh-rsa".getBytes());
      dos.writeInt(rsaPublicKey.getPublicExponent().toByteArray().length);
      dos.write(rsaPublicKey.getPublicExponent().toByteArray());
      dos.writeInt(rsaPublicKey.getModulus().toByteArray().length);
      dos.write(rsaPublicKey.getModulus().toByteArray());
      publicKeyEncoded = new String(Base64.encodeBytes(byteOs.toByteArray()));
      return "ssh-rsa " + publicKeyEncoded;
    } else if (pubKey.getAlgorithm().equals("DSA")) {
      DSAPublicKey dsaPublicKey = (DSAPublicKey) pubKey;
      DSAParams dsaParams = dsaPublicKey.getParams();
      
      ByteArrayOutputStream byteOs = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(byteOs);
      dos.writeInt("ssh-dss".getBytes().length);
      dos.write("ssh-dss".getBytes());
      dos.writeInt(dsaParams.getP().toByteArray().length);
      dos.write(dsaParams.getP().toByteArray());
      dos.writeInt(dsaParams.getQ().toByteArray().length);
      dos.write(dsaParams.getQ().toByteArray());
      dos.writeInt(dsaParams.getG().toByteArray().length);
      dos.write(dsaParams.getG().toByteArray());
      dos.writeInt(dsaPublicKey.getY().toByteArray().length);
      dos.write(dsaPublicKey.getY().toByteArray());
      publicKeyEncoded = new String(Base64.encodeBytes(byteOs.toByteArray()));
      return "ssh-dss " + publicKeyEncoded;
    } else {
      throw new IllegalArgumentException("Unknown public key encoding: " + pubKey.getAlgorithm());
    }
  }
  
  private String decodeType() {
    int len = decodeInt();
    String type = new String(bytes, pos, len);
    pos += len;
    return type;
  }
  
  private int decodeInt() {
    return ((bytes[pos++] & 0xFF) << 24) | ((bytes[pos++] & 0xFF) << 16) | ((bytes[pos++] & 0xFF) << 8)
        | (bytes[pos++] & 0xFF);
  }
  
  private BigInteger decodeBigInt() {
    int len = decodeInt();
    byte[] bigIntBytes = new byte[len];
    System.arraycopy(bytes, pos, bigIntBytes, 0, len);
    pos += len;
    return new BigInteger(bigIntBytes);
  }
  
  public KeyPair generateKeyPair() {
    
    KeyPairGenerator keyPairGenerator;
    try {
      keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      return keyPairGenerator.generateKeyPair();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e.getMessage());
    }
    
  }
  
  public String encryptPrivateKey(KeyPair keyPair, String password) throws InvalidKeySpecException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
    
    PrivateKey privKey = keyPair.getPrivate();
    final byte[] keyBytes = Arrays.copyOf(password.getBytes(), 24);
    DESedeKeySpec keyspec = new DESedeKeySpec(keyBytes);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
    SecretKey key = factory.generateSecret(keyspec);
    
    Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encrypted = cipher.doFinal(privKey.getEncoded());
    
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    bout.write("-----BEGIN RSA PRIVATE KEY-----\n".getBytes());
    bout.write("Proc-Type: 4, ENCRYPTED\n".getBytes());
    bout.write(("DEK-Info: DES-EDE3-CBC, " + Base64.encodeBytes(cipher.getIV()) + "\n").getBytes());
    bout.write(Base64.encodeBytesToBytes(encrypted, 0, encrypted.length, Base64.DO_BREAK_LINES));
    bout.write("\n-----END RSA PRIVATE KEY-----\n".getBytes());
    String encodedCert = new String(bout.toByteArray());
    bout.close();
    return encodedCert;
  }
  
 
  
  public boolean verifyPrivateKey(String encryptedPrivateKey, String password) {
    String ivString = getIVString(encryptedPrivateKey);
    
    try {
      String encryptedKeyString = "";
      if (ivString == null) {
        encryptedKeyString = getUnEncryptedKeyString(encryptedPrivateKey);
      } else {
        encryptedKeyString = getEncryptedKeyString(encryptedPrivateKey);
      }
      
      final byte[] keyBytes = Arrays.copyOf(password.getBytes(), 24);
      DESedeKeySpec keyspec = new DESedeKeySpec(keyBytes);
      
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
      SecretKey key = factory.generateSecret(keyspec);
      
      final IvParameterSpec iv = new IvParameterSpec(Base64.decode(ivString));
      final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
      decipher.init(Cipher.DECRYPT_MODE, key, iv);
      
      byte[] plainText = decipher.doFinal(Base64.decode(encryptedKeyString));
      
      return true;
    } catch (Exception e) {
      return false;
    }
    
  }
  
  private String getEncyrptionAlgorithm(String encryptedPrivateKey) {
    Pattern pattern = Pattern.compile("(DEK-Info:\\s)+(\\S+)+(,)");
    Matcher m = pattern.matcher(encryptedPrivateKey);
    m.find();
    String result = null;
    try {
      result = m.group(2);
    } catch (Exception e) {
      
    }
    return result;
  }
  
  private String getEncryptedKeyString(String encryptedPrivateKey) {
    Pattern pattern = Pattern.compile("(?m)(^DEK[^\\n]*\\n?(.*)(-----END RSA PRIVATE KEY-----))", Pattern.DOTALL);
    Matcher m = pattern.matcher(encryptedPrivateKey);
    
    m.find();
    
    try {
      String result = m.group(2);
      return result;
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    throw new CouldNotParse();
    
  }
  
  private String getIVString(String encryptedPrivateKey) {
    Pattern pattern = Pattern.compile("(DEK-Info:\\s)+(\\S+)+(,\\s)+(\\S+)+(\\s)");
    Matcher m = pattern.matcher(encryptedPrivateKey);
    m.find();
    String result = null;
    try {
      result = m.group(4);
    } catch (Exception e) {
      
    }
    return result;
  }
  
  private String getUnEncryptedKeyString(String key) {
    Pattern pattern = Pattern.compile("(?m)(^-*[^\\n]*\\n?([^\\n]*))", Pattern.DOTALL);
    Matcher m = pattern.matcher(key);
    m.find();
    
    try {
      String result = m.group(2);
      return result;
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    throw new CouldNotParse();
    
  }
  
  public class CouldNotParse extends RuntimeException {
    
    /**
       * 
       */
    private static final long serialVersionUID = -5738049748536007261L;
    
  }
  
}
