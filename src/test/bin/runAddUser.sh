#!/bin/bash

_username="testuser"
_password="password"

type curl >/dev/null 2>&1 || { echo >&2 "I require curl but it's not installed.  Aborting."; exit 1; }

echo "Addding testuser:"
curl -k --request PUT "https://localhost:8443/api/v1/user/${_username}" --data @../resources/${_username}.json -H "Content-type: application/json"

echo "Getting testuser:"
curl -k "https://localhost:8443/api/v1/user/${_username}" --user ${_username}:${_password}

