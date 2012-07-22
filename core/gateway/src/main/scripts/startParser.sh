#!/bin/sh
java -Djavax.net.ssl.trustStore=../.keystore -Djavax.net.ssl.trustStorePassword=changeit -DrepoUser={....} -DrepoPass={....}  -cp teaglegw.jar org.panlab.tgw.restclient.PtmInfoParser
