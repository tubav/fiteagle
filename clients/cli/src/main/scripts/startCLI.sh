#!/bin/bash
_JAR="target/cli-jar-with-dependencies.jar"

[ ! -f "${_JAR}" ] && echo "Run 'mvn package' first!" && exit 1

java -cp ${_JAR} org.teagle.clients.cli.CLI ${@}
