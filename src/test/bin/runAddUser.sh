#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

type curl >/dev/null 2>&1 || { echo >&2 "I require curl but it's not installed.  Aborting."; exit 1; }

curl -s -o /dev/null -w "%{http_code}" -k --request PUT "${_host}/${_username}" --data @${_json} -H "Content-type: application/json"
