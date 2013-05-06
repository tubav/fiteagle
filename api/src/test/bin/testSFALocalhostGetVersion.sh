#!/bin/bash

_getversion="src/main/resources/org/fiteagle/delivery/xmlrpc/sfa/getversion_request.xml"
_listresources="src/main/resources/org/fiteagle/delivery/xmlrpc/sfa/listresources_request.xml"
_url_plain="http://localhost:9111/xmlrpc/"
_url_ssl="https://localhost:9112/xmlrpc/sfa/am/v3"
_omni="/opt/gcf/current/src/omni.py"
_omni_name="fiteagle"

function sendRequest {
  echo "Sending request to ${1}..."
  echo " * Content: ${2}"
  
  if [ "${3}" == "" ]; then
    content="`curl -ks --data @${2} ${1}`"
  else
    content="`curl -ks --data @${2} --key ${3} ${1}`"
  fi
  result=$?
  if [ "$result" == "0" ] && [ "$content" == "" ]; then
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

#sendRequest ${_url_plain} ${_getversion}
#actual=$?; expected="0"; assertEqual $actual $expected;

#sendRequest ${_url_plain} ${_listresources}
#actual=$?; expected="0"; assertEqual $actual $expected;

sendRequest ${_url_ssl} ${_getversion}
actual=$?; expected="35"; assertEqual $actual $expected;

if [ ! -f "${_omni}"]; then
  echo "Skipped: ${_omni} not found";
else
  python ${_omni} getversion -a ${_omni_name}
  actual=$?; expected="0"; assertEqual $actual $expected;
fi

exit 0
