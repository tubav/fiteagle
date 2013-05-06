#!/bin/bash

mkdir -p target
keytool -genkey -alias jetty6 -keyalg RSA -keystore target/jetty-ssl.keystore -storepass jetty6 -keypass jetty6 -dname "CN=fiteagle.org"
