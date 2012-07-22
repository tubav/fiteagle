For Developers
==============

General
-------
This repository is divided into sub projects that should all follow the same procedure
that is described here.

First steps
-----------

The following commands should download all required libraries and compile every module
within this project. After this you can start for example the CLI.

    git clone https://github.com/tubav/fiteagle.git
    cd fiteagle
    mvn compile
    cd clients/cli
    mvn package
    ./src/main/scripts/startCLI.sh

Create a new module
-------------------

    mkdir path/to/module
    cd path/to/module/..
    nano pom.xml #add gateway module
    cd -
    cp ../othermodule/pom.xml . # or modify existing file
    nano pom.xml #change details
    #copy or create src directory
    mvn compile # repeat this until it compiles

Integrate into Eclipse
----------------------
 * Eclipse: Install m2e plugin
 * CLI: mvn eclipse:eclipse
 * Eclipse: File > import > Git > Project from Git > Local > ((add your local clone repo)) > Import existing projects > 
 * CLI: mvn eclipse:clean
 * Eclipse: right click on the project > Configure > Convert to Maven
