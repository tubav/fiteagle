#!/bin/bash

_input="src/main/resources/org/fiteagle/delivery/xmlrpc/sfa_getVersion.xml"
_url_plain="http://localhost:9111/xmlrpc/"
_url_ssl="https://localhost:9112/xmlrpc/"

function sendRequest {
  echo "Sending request to ${1}..."
  echo " * Content: ${2}"
  
  if [ "${3}" == "" ]; then
    content="`curl -ks --data @${2} ${1}`"
  else
    content="`curl -ks --data @${2} --key ${3} ${1}`"
  fi
  result=$?
  if [ "$content" == "" ]; then
    result="111"
  fi
  echo " * Answer: "
  echo "-------------------------------"
  echo $content
  echo "-------------------------------"
  echo ""
  return $result
}

function assertEqual {
  actual=$1; expected=$2;
  if [ "$actual" != "$expected" ]; then
    echo "FAILED. Expected error code $expected. I got: $actual."
    exit 1
  fi
}

sendRequest ${_url_plain} ${_input}
actual=$?; expected="0"; assertEqual $actual $expected;

sendRequest ${_url_ssl} ${_input}
actual=$?; expected="35"; assertEqual $actual $expected;

//sendRequest ${_url_ssl} ${_input} ${_key} 

exit 0
