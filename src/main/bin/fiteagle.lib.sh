#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
_main_dir="$DIR/../../../delivery/interfaces"  
_log="`mktemp -t fiteagle.XXXXX`"

function testFITeagle {  
  echo "Testing and bootstraping FITeagle (this might take a while - have a look at '${_log}')..."
  mvn -B install > "${_log}" 2>&1
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look at '${_log}'."
    exit 3
  fi
}

function startFITeagle {
  echo "Starting FITeagle..."
  cd "${_main_dir}"
  [ -x ./src/main/bin/ssl_create_server_cert.sh ] || { echo "ERROR 5"; exit 5; }
  [ -f target/jetty-ssl.keystore ] || ./src/main/bin/ssl_create_server_cert.sh
  mvn -B -q jetty:run
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 3
  fi
}

function stopFITeagle {
  echo "Stopping FITeagle..."
  cd "${_main_dir}"
  mvn -B -q jetty:stop
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 3
  fi
}


function usage {
    echo "Usage: ./src/main/bin/fiteagle.sh test|start|stop"
}

