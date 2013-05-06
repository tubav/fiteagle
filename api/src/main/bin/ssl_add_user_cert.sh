#!/bin/bash


if [ ! -f "$1" ] || [ "" == "$2" ]; then
  echo "Usage: $0 <cer file> <alias>"
  exit 1
fi

mkdir -p target
keytool -importcert -noprompt -storepass jetty6 -file "${1}" -keystore target/jetty-ssl.keystore -alias "${2}" 
