#!/bin/bash
_PARAMS=""
_JAR="target/vcttool-jar-with-dependencies.jar"

[ $(uname) == "Darwin" ] && _PARAMS="${_PARAMS} -XstartOnFirstThread"
[ ! -f "${_JAR}" ] && echo "Running 'mvn package' first..." && mvn package

java ${_PARAMS} -jar ${_JAR}
