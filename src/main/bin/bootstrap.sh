#!/usr/bin/env bash

echo "Usage: bootstrap.sh [<directory>]"
echo ""

if [ -n "$1" ]; then _target="$1"; else _target="./fiteagle"; fi
_target_base="`dirname ${_target}`"

function checkBinary {
  echo -n " * Checking for '$1'..."
  if command -v $1 >/dev/null 2>&1; then
     echo "OK"
     return 0
   else
     echo >&2 "FAILED."
     return 1
   fi
}

function checkEnvironment {
  _error=0
  echo "Checking environment..."
  checkBinary java; _error=$(($_error + $?))
  checkBinary javac; _error=$(($_error + $?))
  checkBinary mvn; _error=$(($_error + $?))
  checkBinary git; _error=$(($_error + $?))
  checkFITeagleInstallation; _error=$(($_error + $?))
  if [ "0" != "$_error" ]; then
    echo >&2 "FAILED. Please install the above mentioned binaries."
    exit 1
  fi
}

function checkFITeagleInstallation {
  echo -n " * Checking for 'FITeagle'..."
  if [ -x "${_target}/src/main/bin/fiteagle.sh" ]; then
    if [ -w "${_target}/" ]; then 
      echo "OK (found at '${_target}')"
    else
      echo "FAILED. (no write permissions in '${_target}')"
      return 1
    fi
  else
    if [ -w "${_target_base}" ]; then 
      echo "OK (will install it to '${_target}')"
    else
      echo "FAILED (no write permissions to create '${_target}')"
      return 1
    fi
  fi
  return 0
}

function installFITeagle {
  repo="fiteagle"
  git_url="git://github.com/tubav/${repo}"
  
  if [ -d "${_target}/.git" ]; then
    echo -n "Updating FITeagle..."
    (cd "${_target}" && git pull -q)
  else
    echo -n "Installing FITeagle..."
    git clone -q --recursive --depth 1 ${git_url} ${_target}
  fi
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 2
  fi
  
  echo "OK"
}

checkEnvironment
installFITeagle

cd ${_target}
./src/main/bin/fiteagle.sh test
./src/main/bin/fiteagle.sh start