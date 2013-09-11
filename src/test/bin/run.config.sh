#!/bin/bash

_username="testuser"
_password="Password"
_json="${_dir}/../resources/${_username}.json"
_host="https://localhost:8443/api/v1/user"
_cert="/tmp/${_username}.pem"


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

function require() {
  error=0;
  for bin in "$@"; do
    type $bin >/dev/null 2>&1 || { echo >&2 "WARNING: I require $bin but it's not installed. (skipping this test)"; error=1; }
  done
  [ $error == 1 ] && exit 0;
}
