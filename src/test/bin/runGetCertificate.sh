#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require curl

curl -s -o ${_cert} -w "%{http_code}" -k --request POST "${_host}/${_username}/certificate" --data "${_password}" -H "Content-type: text/plain" --user ${_username}:${_password}
