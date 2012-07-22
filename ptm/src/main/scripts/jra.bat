@ECHO OFF



IF "%1" == "" GOTO noarg



SET PTM_HOME=%~dp0..



ECHO PTM_HOME: %PTM_HOME%



SET RADIR=%PTM_HOME%\ra


SET MODULEDIR=%PTM_HOME%\lib



SET CP=%RADIR%;%MODULEDIR%;%MODULEDIR%\commons-cli-1.2.jar;%MODULEDIR%\commons-logging-1.1.jar;%MODULEDIR%\jdom.jar;%MODULEDIR%\log4j.jar;%MODULEDIR%\ptm.jar;%MODULEDIR%\ws-commons-util-1.0.2.jar;%MODULEDIR%\xmlrpc-client-3.1.1.jar;%MODULEDIR%\xmlrpc-common-3.1.1.jar;%MODULEDIR%\xmlrpc-server-3.1.1.jar



ECHO CLASSPATH: %CP%



ECHO starting RA...



java -cp %CP% %1



GOTO end



:noarg



ECHO No argument given. Please specify an adapter class.



:end
