#!/bin/bash
_PARAMS=""
_JAR="target/vcttool-jar-with-dependencies.jar"

[ $(uname) == "Darwin" ] && _PARAMS="${_PARAMS} -XstartOnFirstThread"
[ ! -f "${_JAR}" ] && echo "Run 'mvn package' first!" && exit 1

java ${_PARAMS} -jar ${_JAR}
