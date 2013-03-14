#!/usr/bin/env bash

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
  if [ "0" != "$_error" ]; then
    echo >&2 "FAILED. Please install the above mentioned binaries."
    exit 1
  fi
}

function installFITeagle {
  _git_target="fiteagle"
  _git_url="git://github.com/tubav/${_git_target}"

  if [ -d "${_git_target}" ]; then
    echo "Updating FITeagle..."
    (cd "${_git_target}" && git pull)
  else
    echo "Installing FITeagle..."
    git clone --recursive --depth 1 ${_git_url}
  fi
  
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 2
  fi
}

function testFITeagle {
  _target="fiteagle"
  
  echo "Testing FITeagle..."
  cd "${_target}" && mvn test
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 3
  fi
}

function runFITeagle {
  _target="fiteagle/delivery/xmlrpc"
  
  echo "Starting FITeagle..."
  cd "${_target}"
  [ -e target/jetty-ssl.keystore ] || ./src/main/bin/ssl_create_server_cert.sh
  mvn jetty:run
  if [ "0" != "$?" ]; then
    echo >&2 "FAILED. Please have a look above."
    exit 3
  fi
}

function usage {
    echo "Usage: $0 install|update|test|start"
}

case $1 in
    install) checkEnvironment; installFITeagle;;
    update) checkEnvironment; installFITeagle;;
    test) testFITeagle;;
    start) runFITeagle;;
    *) usage
esac
