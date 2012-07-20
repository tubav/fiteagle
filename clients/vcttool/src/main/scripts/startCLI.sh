#!/bin/bash
_JAR="target/vcttool-jar-with-dependencies.jar"

[ ! -f "${_JAR}" ] && echo "Run 'mvn package' first!" && exit 1

java -cp ${_JAR} org.teagle.api.CLI ${@}
