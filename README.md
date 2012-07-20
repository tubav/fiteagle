FITeagle
========

General
-------

This repository is divided into sub projects that should all follow the same procedure
that is described here.

Common tasks
------------

1. Compile: mvn -T2 compile
2. Start application: mvn package && ./src/main/scripts/startXXX.sh

Open in Eclipse
---------------
1. mvn eclipse:eclipse
2. Start eclipse and go to "Eclipse > Preferences > Java > Build Path > Classpath Variables" and create a new variable "M2_REPO" and point it e.g. to "/Users/USERNAME/.m2/repository".
3. Import the project
