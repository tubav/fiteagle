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

First check out the code:

    git clone https://github.com/tubav/fiteagle.git
    cd fiteagle

Now have a look at the documentation:

    mvn -N site
    open target/site/index.html

Try to test the whole project:

    mvn test

Start for example the CLI:

    cd clients/cli
    ./src/main/scripts/startCLI.sh
    
Setup deployment
-----------------

Edit the file .m2/settings.xml:

    ?xml version="1.0"?>
    <settings>
        <servers>
            <server>
                <id>fiteagle</id>
                    <username>xxx</username>
                    <password>xxx</password>
            </server>
        </servers>
    </settings>

Checkin new code
-----------------

    cd path/to/project/root
    mvn -T3 clean test
    mvn deploy
    git status
    git add path/to/new/files
    git commit -m "Fixed xyz" .
    git push

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
