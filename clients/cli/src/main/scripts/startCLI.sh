#!/bin/bash
_JAR="target/cli-jar-with-dependencies.jar"

[ ! -f "${_JAR}" ] && echo "Running 'mvn package' first..." && mvn package

java -cp ${_JAR} org.teagle.clients.cli.CLI ${@}
