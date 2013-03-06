#!/bin/bash

_input="src/main/resources/org/fiteagle/delivery/xmlrpc/sfa_getVersion.xml"
_url_plain="http://localhost:8080/xmlrpc/"
_url_ssl="https://localhost:8443/xmlrpc/"


function sendRequest {
  echo "Sending request to ${1}..."
  echo " * Content: ${2}"
  echo " * Answer: "
  echo "-------------------------------"
  curl -k --data @${2} ${1}
  result=$?
  echo "-------------------------------"
  echo ""
  return $result
}

sendRequest ${_url_plain} ${_input}
if [ "$?" != "0" ]; then
  echo "FAILED. Expected a success."
fi

sendRequest ${_url_ssl} ${_input}
if [ "$?" != "36" ]; then
  echo "FAILED. Expected error 35 (client must authenticate)."
fi

exit 0