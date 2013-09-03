#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require curl omni.py

./src/test/bin/runDelUser.sh > /dev/null

result=$(./src/test/bin/runDelUser.sh)
asssert "testDelUser" "404" "$result"

result=$(./src/test/bin/runAddUser.sh)
asssert "testAddUser" "201" "$result"

result=$(./src/test/bin/runGetUser.sh)
asssert "testGetUser" "200" "$result"

result=$(./src/test/bin/runGetCertificate.sh)
asssert "testGetCertificate" "200" "$result"

result=$(./src/test/bin/runOmniGetVersionAuto.sh|grep -i "f4f_endorsed_tools")
asssert "testOmniGetVersion" "f4f_endorsed_tools" "$result"

result=$(./src/test/bin/runOmniListResourcesAuto.sh|grep -i "available")
asssert "testOmniListResources" "available" "$result"
