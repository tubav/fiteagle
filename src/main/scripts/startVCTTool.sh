#!/bin/bash
_PARAMS=""
[ $(uname) == "Darwin" ] && _PARAMS="${_PARAMS} -XstartOnFirstThread"
java ${_PARAMS} -jar target/vcttool-jar-with-dependencies.jar
