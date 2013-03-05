#!/bin/bash

_input="src/main/resources/org/fiteagle/delivery/xmlrpc/sfa_getVersion.xml"
_url="http://localhost:8080/xmlrpc/"

echo "Sending request to ${_url}..."
echo " * Content: ${_input}"
echo " * Answer: "

curl --data @${_input} ${_url}
#curl --data @src/main/resources/org/fiteagle/delivery/xmlrpc/sfa_getVersion.xml http://localhost:8080/xmlrpc
#curl $_url

echo "foo"
