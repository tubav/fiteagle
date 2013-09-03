#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require curl

curl -s -o /dev/null -w "%{http_code}" -k --request PUT "${_host}/${_username}" --data @${_json} -H "Content-type: application/json"
