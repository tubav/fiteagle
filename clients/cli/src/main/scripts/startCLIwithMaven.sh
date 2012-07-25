#!/bin/bash

_MAIN="org.teagle.clients.cli.CLI"

# compared to the jar execution: slower but always uses the latest code
mvn -q exec:java -Dexec.mainClass="${_MAIN}" -Dexec.classpathScope=runtime -Dexec.args="${@}"

# compared to the above implementation: faster but not always uses the latest code
#[ ! ".cp.txt" ] && echo "Creating class path..." && mvn -q dependency:build-classpath -Dmdep.outputFile=.cp.txt
#export CLASSPATH="target/classes:`cat .cp.txt`"
#java -cp $CLASSPATH "${_MAIN}" ${@}
