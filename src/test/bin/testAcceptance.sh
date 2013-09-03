#!/bin/bash

: > log
./src/test/bin/runAddUser.sh >> log
./src/test/bin/runOmniGetVersionAuto.sh >> log

result=$(./src/test/bin/runOmniGetVersionAuto.sh|grep -i f4f_endorsed_tools)

echo -n "GetVersion: "
if [ "$result" == "" ]; then
  echo "FAILED (I require the f4f_endorsed_tools tag in the getVersion call but I did not found it)"; exit 1;
else
  echo "PASSED"
fi

