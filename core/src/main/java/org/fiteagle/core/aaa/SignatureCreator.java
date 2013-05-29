package org.fiteagle.core.aaa;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.cert.X509Certificate;


import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.ElementProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SignatureCreator {
  Logger log = LoggerFactory.getLogger(getClass()); 
  public ByteArrayOutputStream signContent(InputSource credential, String credentialId) throws Exception {
  
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(credential);
   
    Element element =  ((Element)doc.getElementsByTagName("credential").item(0));
    element.setAttribute("id",credentialId);
    element.setIdAttribute("id",true);
  
    Init.init();
    ElementProxy.setDefaultPrefix(Constants.SignatureSpecNS, "");
    KeyStoreManagement keyStoreManagement = KeyStoreManagement.getInstance();
    XMLSignature sig = new XMLSignature(doc, null, XMLSignature.ALGO_ID_SIGNATURE_RSA);
    Transforms transforms = new Transforms(doc);
    transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
    sig.addDocument("#"+credentialId, transforms, Constants.ALGO_ID_DIGEST_SHA1);
    Key privateKey = keyStoreManagement.getCAPrivateKey();
    X509Certificate cert = keyStoreManagement.getCACert();
    sig.addKeyInfo(cert);
    sig.addKeyInfo(cert.getPublicKey());
    sig.sign(privateKey);
    NodeList children = doc.getFirstChild().getChildNodes();
    for(int i = 0; i< children.getLength();i++){
      Node n = children.item(i);
      if(n.getNodeName().equals("signatures")){
        n.appendChild(sig.getElement());
      }
    }
    final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.write(Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_WITH_COMMENTS).canonicalizeSubtree(doc));
  
    return outputStream;
}

}
