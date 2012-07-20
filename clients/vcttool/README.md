FITeagle VCTTool
================

Compile
-------

1. ./prepare.sh (until we've all jar file on the repository)
2. mvn -T2 compile

Start VCTTool
-------------
1. mvn package
2. ./src/main/scripts/startVCTTool.sh

Start CLI
-------------
1. mvn compile
2. ./src/main/scripts/startCLI.sh


Open in Eclipse
---------------
1. mvn eclipse:eclipse
2. Start eclipse and go to "Eclipse > Preferences > Java > Build Path > Classpath Variables" and create a new variable "M2_REPO" and point it e.g. to "/Users/USERNAME/.m2/repository".
3. Import the project
