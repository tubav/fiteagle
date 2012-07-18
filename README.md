OpenTeagle_VCTTool
==================

Getting started
---------------

(preliminary - this will change)

1. ./prepare.sh (until we've a nexus server)
2. mvn clean package
3. ./src/main/scripts/startVCTTool.sh

FAQ
---
 * Issue: When opening the Eclipse workspace, you might get an error message like: "unbound classpath variable m2_repo".
   * Solution: Go to "Eclipse > Preferences > Java > Build Path > Classpath Variables" and create a new variable "M2_REPO" and point it e.g. to "/Users/USERNAME/.m2/repository".
 
