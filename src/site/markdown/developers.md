For Developers
==============

General
-------
This repository is divided into sub projects that should all follow the
same procedure that is described here. When changing or adding code we
strongly suggest you to follow the principles of the [Clean Code
Developer](http://www.clean-code-developer.com) web site and maybe to
have a look at [Uncle Bob's Clean Code](http://www.cleancoders.com/)
video series.

First steps
-----------

The following commands should download all required libraries and
compile every module within this project. After this you can have a look at
the documentation or start for example the CLI.

    git clone https://github.com/tubav/fiteagle.git
    cd fiteagle
    mvn compile
    mvn -N site
    open target/site/index.html
    cd clients/cli
    mvn package
    ./src/main/scripts/startCLI.sh

Create a new module
-------------------

    mkdir path/to/module
    cd path/to/module/..
    nano pom.xml #add the new module
    cd -
    cp ../othermodule/pom.xml . # or modify existing file
    nano pom.xml #change details
    #copy or create src/main/java directory
    mvn compile # repeat this until it compiles

Integrate into Eclipse
----------------------
 * Eclipse: Install m2e plugin
 * CLI: mvn eclipse:eclipse
 * Eclipse: File > import > Git > Project from Git > Local >
   ((add your local clone repo)) > Import existing projects > 
 * CLI: mvn eclipse:clean
 * Eclipse: right click on the project > Configure > Convert to Maven
