#!/usr/bin/env bash
function testFITeagle {  
  echo -n "Testing FITeagle (this might take a while)..."
  mvn -q test
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 3
  fi
  
  echo "OK"
}

function runFITeagle {
  main_dir="./delivery/xmlrpc"  
  echo "Starting FITeagle..."
  cd "${main_dir}"
  [ -x ./src/main/bin/ssl_create_server_cert.sh ] || { echo "ERROR 5"; exit 5; }
  [ -f target/jetty-ssl.keystore ] || ./src/main/bin/ssl_create_server_cert.sh
  mvn -q jetty:run
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 3
  fi
}

function usage {
    echo "Usage: ./src/main/bin/fiteagle.sh test|start"
}

case $1 in
    test) testFITeagle;;
    start) runFITeagle;;
    *) usage
esac
