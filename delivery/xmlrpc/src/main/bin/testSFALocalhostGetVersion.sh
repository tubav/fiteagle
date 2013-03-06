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
  echo "-------------------------------"
  echo ""
}

sendRequest ${_url_plain} ${_input}
sendRequest ${_url_ssl} ${_input}
