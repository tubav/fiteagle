#!/bin/sh
java -Xms512m -Dcom.sun.grizzly.keepAliveLockingThread=true -Djavax.net.ssl.trustStore=../.keystore -Djavax.net.ssl.keyStore=../keystore -Djavax.net.ssl.trustStorePassword=changeit  -Djavax.net.ssl.keyStorePassword=changeit -Dteaglegw.timeout=500000 -Dsingleton.thread=8000 -DrepoUser={....} -DrepoPass={....} -DpublicIP=127.0.0.1 -jar teaglegw.jar

