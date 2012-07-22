/**
 *  Copyright 2010, Konstantinos Koutsopoulos (k.koutsopoulos@yahoo.gr), Nikos Mouratidis (nmouratid@teemail.gr)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.panlab.tgw.restclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.panlab.tgw.util.XMLElement;
import org.panlab.tgw.util.XMLUtil;

import teagle.vct.model.Ptm;

/**
 *
 * @author kkoutso
 */
public class PtmInfoParser
{

    private static Log log = LogFactory.getLog(PtmInfoParser.class);

    private static byte[] getClientHello()
    {
        long time = System.currentTimeMillis();
        time /= 1000;
        //System.out.println(time + " ");
        int index = 0;
        String[] suite = ((SSLSocketFactory) SSLSocketFactory.getDefault()).getDefaultCipherSuites();
        for (int j = 0; j < suite.length; j++)
        {

            if (suites.containsKey(suite[j]))
            {
                index++;
                //System.out.println(suite[j]);
                }
        }
        int tempIndex = 0;
        byte[] suiteBytes = new byte[index * 3];
        for (int j = 0; j < suite.length; j++)
        {
            //log.info(j+": ");
            if (suites.containsKey(suite[j]))
            {
                //log.info(suite[j]);
                suiteBytes[3 * (index - tempIndex) - 3] = suites.get(suite[j])[0];
                suiteBytes[3 * (index - tempIndex) - 2] = suites.get(suite[j])[1];
                suiteBytes[3 * (index - tempIndex) - 1] = suites.get(suite[j])[2];
                tempIndex++;
            }
        }


        byte[] clientHello =
        {
            (byte) 0x80, (byte) (41 + suiteBytes.length), //Length
            0x01, //Message Type: Client Hello
            0x03, 0x01, //Version TLSv1.0
            (byte) (suiteBytes.length / 256), (byte) (suiteBytes.length % 256), //Cipher Spec Length
            0x00, 0x00, //Session ID Length
            0x00, 0x20, //Challenge Length
        };
        byte[] clientRandom =
        {
            (byte) (time / (256 * 256 * 256)),
            (byte) ((time % (256 * 256 * 256)) / (256 * 256)),
            (byte) (((time % (256 * 256 * 256)) % (256 * 256)) / 256),
            (byte) (time % 256),
            0x00, 0x01, 0x02, 0x03, 0x04,
            0x00, 0x01, 0x02, 0x03, 0x04,
            0x00, 0x01, 0x02, 0x03, 0x04,
            0x00, 0x01, 0x02, 0x03, 0x04,
            0x00, 0x01, 0x02, 0x03, 0x04,
            0x00, 0x01, 0x0f
        };

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(clientHello, 0, clientHello.length);
        baos.write(suiteBytes, 0, suiteBytes.length);
        baos.write(clientRandom, 0, clientRandom.length);

        return baos.toByteArray();
    }
    private static Hashtable<String, byte[]> suites = new Hashtable<String, byte[]>();

    static
    {
        suites.put("TLS_RSA_WITH_NULL_MD5", new byte[]
                {
                    0x00, 0x00, 0x01
                });
        suites.put("TLS_RSA_WITH_NULL_SHA", new byte[]
                {
                    0x00, 0x00, 0x02
                });
        suites.put("TLS_RSA_WITH_NULL_SHA256", new byte[]
                {
                    0x00, 0x00, 0x3B
                });
        suites.put("TLS_RSA_WITH_RC4_128_MD5", new byte[]
                {
                    0x00, 0x00, 0x04
                });
        suites.put("TLS_RSA_WITH_RC4_128_SHA", new byte[]
                {
                    0x00, 0x00, 0x05
                });
        suites.put("TLS_RSA_WITH_3DES_EDE_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x0A
                });
        suites.put("TLS_RSA_WITH_AES_128_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x2F
                });
        suites.put("TLS_RSA_WITH_AES_256_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x35
                });
        suites.put("TLS_RSA_WITH_AES_128_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x3C
                });
        suites.put("TLS_RSA_WITH_AES_256_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x3D
                });
        suites.put("TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x0D
                });
        suites.put("TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x10
                });
        suites.put("TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x13
                });
        suites.put("TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x16
                });
        suites.put("TLS_DH_DSS_WITH_AES_128_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x30
                });
        suites.put("TLS_DH_RSA_WITH_AES_128_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x31
                });
        suites.put("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x32
                });
        suites.put("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x33
                });
        suites.put("TLS_DH_DSS_WITH_AES_256_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x36
                });
        suites.put("TLS_DH_RSA_WITH_AES_256_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x37
                });
        suites.put("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x38
                });
        suites.put("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x39
                });
        suites.put("TLS_DH_DSS_WITH_AES_128_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x3E
                });
        suites.put("TLS_DH_RSA_WITH_AES_128_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x3F
                });
        suites.put("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x40
                });
        suites.put("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x67
                });
        suites.put("TLS_DH_DSS_WITH_AES_256_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x68
                });
        suites.put("TLS_DH_RSA_WITH_AES_256_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x69
                });
        suites.put("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x6A
                });
        suites.put("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x6B
                });
        suites.put("TLS_DH_anon_WITH_RC4_128_MD5", new byte[]
                {
                    0x00, 0x00, 0x18
                });
        suites.put("TLS_DH_anon_WITH_3DES_EDE_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x1B
                });
        suites.put("TLS_DH_anon_WITH_AES_128_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x34
                });
        suites.put("TLS_DH_anon_WITH_AES_256_CBC_SHA", new byte[]
                {
                    0x00, 0x00, 0x3A
                });
        suites.put("TLS_DH_anon_WITH_AES_128_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x6C
                });
        suites.put("TLS_DH_anon_WITH_AES_256_CBC_SHA256", new byte[]
                {
                    0x00, 0x00, 0x6D
                });
    }

    private static void getPTMCertificate(String alias, URL url)
    {
        //if(App.ptm_indexes.containsKey(alias))
        //    return;

        try
        {

            Socket socket = new Socket(url.getHost(), url.getPort());

            OutputStream os = socket.getOutputStream();
            os.write(getClientHello());
            os.flush();
            os.flush();

            InputStream is = socket.getInputStream();

            byte[] buffer = new byte[2000];
            int length = 0;
            int tries = 0;
            while (true)
            {
                length += is.read(buffer, length, buffer.length - length);
                if (length > 0 && tries == 0)
                {
                    tries++;
                    if (buffer[0] == 0x16)
                    {
                        log.info("TLSv1 Handshake");
                        log.info("Version: " + getText(buffer, 1, 2));
                        log.info("Length: " + getInt(buffer, 3, 2));
                        byte[] tmp = new byte[getInt(buffer, 3, 2) + 5];
                        System.arraycopy(buffer, 0, tmp, 0, length);
                        buffer = tmp;
                    }
                }
                //log.info("Length: "+length);
                if (buffer.length == length)
                {
                    break;
                }
            }
            //log.info("Length: "+length);
            int index = 0;
            if ((index = locateServerCertificate(buffer)) > 0)
            {
                log.info("Certificates Section Located at: " + index);
                length = getInt(buffer, index + 1, 3);
                log.info("Certificates length: " + length);
                length = getInt(buffer, index + 7, 3);
                log.info("First Certificate length: " + length);
                //log.info(getText(buffer, index+10, length));

                byte[] cert = new byte[length];
                System.arraycopy(buffer, index + 10, cert, 0, length);

                X509Certificate x509 = X509Certificate.getInstance(cert);
                log.info(x509.getSubjectDN().toString().replace(", ", ","));
                processCertificate(alias, x509, url);

            }
        } catch (Exception error)
        {
            log.error(error.getMessage());
        }

    }

    public static void refreshPTMs()
    {
        try
        {
            for (Ptm ptm : RepoAdapter.getPtms())
            {
                {
                    log.info(ptm.getCommonName() + " " + ptm.getUrl());
                    URL url = new URL(ptm.getUrl());
                    getPTMCertificate(ptm.getCommonName(), url);
                }
            }

        } catch (Exception error)
        {
            error.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        try
        {
            refreshPTMs();

        } catch (Exception error)
        {
            log.error(error.getMessage());
        }
    }

    private static String getText(byte[] array, int start, int length)
    {
        int i;
        String str = "0x", tmp;
        for (i = start; i < start + length; i++)
        {
            tmp = Integer.toHexString(array[i]);
            str += tmp.length() == 2 ? tmp : (tmp.length() == 1 ? "0" + tmp : tmp.substring(6));
        }
        return str;
    }

    private static int getInt(byte[] buffer, int start, int length)
    {
        int result = 0;
        int tmp;
        for (int j = start; j < start + length; j++)
        {
            if (buffer[j] < 0)
            {
                tmp = 256 + buffer[j];
            } else
            {
                tmp = buffer[j];
            }

            result += (int) (tmp * Math.pow(256, start + length - 1 - j));
        }
        return result;
    }

    private static int locateServerCertificate(byte[] buffer)
    {
        int index = 5;
        while (buffer[index] != 0x0b)
        {
            index += 4 + getInt(buffer, index + 1, 3);
            if (index > buffer.length)
            {
                return -1;
            }
            log.info("" + index);
        }
        return index;

    }

    private static void processCertificate(String alias, X509Certificate x509, URL url)
    {
        try
        {
            String store = System.getProperty("javax.net.ssl.trustStore");
            String password = System.getProperty("javax.net.ssl.trustStorePassword");

            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(new FileInputStream(store), password.toCharArray());

            Enumeration<String> en = keystore.aliases();
            while (en.hasMoreElements())
            {
                log.info(en.nextElement());
            }

            if (!keystore.containsAlias(alias))
            {
                ByteArrayInputStream bais = new ByteArrayInputStream(x509.getEncoded());
                Certificate cert = CertificateFactory.getInstance("x509").generateCertificate(bais);
                keystore.setCertificateEntry(alias, cert);

                storeNewPTM(alias, url, x509.getSubjectDN().toString().replace(", ",","));


                en = keystore.aliases();
                while (en.hasMoreElements())
                {
                    log.info(en.nextElement());
                }
                keystore.store(new FileOutputStream(store), password.toCharArray());

                TrustManagerFactory.getInstance("PKIX").init(keystore);
            }




        } catch (Exception error)
        {
            log.error(error.getMessage());
        }
    }

    private static void storeNewPTM(String alias, URL url, String dn)
    {
        try
        {

            File file = new File("conf.xml");
            String content;
            if(file.exists())
            {
                FileInputStream fis = new FileInputStream(file);
                int length = fis.available();
                byte bArray[] = new byte[length];
                fis.read(bArray);
                fis.close();
                content = new String(bArray);
            }
            else
                content = "<ptms></ptms>";

            Object objs[] = XMLUtil.getElements(content);
            FileOutputStream fos = new FileOutputStream("conf.xml");

            fos.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n".getBytes());
            fos.write("<ptms>\n".getBytes());

            if (objs != null)
            {
                for (int i = 0; i < objs.length; i++)
                {
                    XMLElement element = (XMLElement) (objs[i]);
                    fos.write((element.toString()+"\n").getBytes());
                }
            }
            XMLElement elem = new XMLElement(alias, "cert", "\""+dn+"\"", url.toString());
            fos.write((elem.toString()+"\n").getBytes());
            fos.write("</ptms>\n".getBytes());
            fos.close();
            
        } catch (Exception error)
        {
            log.error(error.getMessage());
        }
    }
}
