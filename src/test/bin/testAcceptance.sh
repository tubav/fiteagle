#!/bin/bash

function asssert() {
  testcase="$1"
  expected="$2"
  result="$3"
  echo -n "$testcase: "
  if [[ "$result" == *"$expected"* ]]; then
    echo "PASSED"
  else
    echo "FAILED (I expected '*$expected*' but got '$result')"; exit 1;
  fi
}

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
