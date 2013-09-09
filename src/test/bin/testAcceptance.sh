#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require curl omni.py

./src/test/bin/runDelUser.sh > /dev/null

expect="404"
result=$(./src/test/bin/runDelUser.sh)
asssert "testDelUser" "$expect" "$result"

expect="201"
result=$(./src/test/bin/runAddUser.sh)
asssert "testAddUser" "$expect" "$result"

expect="200"
result=$(./src/test/bin/runGetUser.sh)
asssert "testGetUser" "$expect" "$result"

expect="200"
result=$(./src/test/bin/runGetCertificate.sh)
asssert "testGetCertificate" "$expect" "$result"

expect="fiteagle version"
result=$(./src/test/bin/runOmniGetVersionAuto.sh|grep -i "$expect")
asssert "testOmniGetVersion" "$expect" "$result"

expect="<rspec"
result=$(./src/test/bin/runOmniListResourcesAuto.sh|grep -i "$expect")
asssert "testOmniListResources" "$expect" "$result"
