#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require curl

${_dir}/runDelUser.sh > /dev/null

expect="404"
result=$(${_dir}/runDelUser.sh)
asssert "testDelUser" "$expect" "$result"

expect="201"
result=$(${_dir}/runAddUser.sh)
asssert "testAddUser" "$expect" "$result"

expect="200"
result=$(${_dir}/runGetUser.sh)
asssert "testGetUser" "$expect" "$result"

expect="200"
result=$(${_dir}/runGetCertificate.sh)
asssert "testGetCertificate" "$expect" "$result"

require omni.py

expect="fiteagle version"
result=$(${_dir}/runOmniGetVersion.sh|grep -i "$expect")
asssert "testOmniGetVersion" "$expect" "$result"

expect="<rspec"
result=$(${_dir}/runOmniListResources.sh|grep -i "$expect")
asssert "testOmniListResources" "$expect" "$result"
