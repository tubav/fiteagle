#!/bin/bash
_PARAMS=""
[ $(uname) == "Darwin" ] && _PARAMS="${_PARAMS} -XstartOnFirstThread"
java ${_PARAMS} -jar target/vcttool-0.1/vct-0.1.jar
