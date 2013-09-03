#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

type curl >/dev/null 2>&1 || { echo >&2 "I require curl but it's not installed.  Aborting."; exit 1; }

curl -s -o ${_cert} -w "%{http_code}" -k --request POST "${_host}/${_username}/certificate" --data "${_password}" -H "Content-type: text/plain" --user ${_username}:${_password}
