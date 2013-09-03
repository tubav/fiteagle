#!/bin/bash

_username="testuser"
_password="Password"
_json="./src/test/resources/${_username}.json"
_host="https://localhost:8443/api/v1/user"
_cert="/tmp/${_username}.pem"

type curl >/dev/null 2>&1 || { echo >&2 "I require curl but it's not installed.  Aborting."; exit 1; }

echo
echo "Addding testuser:"
curl -sk --request PUT "${_host}/${_username}" --data @${_json} -H "Content-type: application/json"

echo
echo "Getting testuser:"
curl -sk "${_host}/${_username}" --user ${_username}:${_password}

echo
echo "Downloading certificate to ${_cert}:"
curl -sk --request POST "${_host}/${_username}/certificate" --data "${_password}" -H "Content-type: text/plain" --user ${_username}:${_password} > ${_cert}
