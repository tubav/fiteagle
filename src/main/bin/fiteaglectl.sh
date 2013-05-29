#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

. "${DIR}/fiteagle.lib.sh"

case $1 in
    test) testFITeagle;;
    start) startFITeagle;;
    stop) stopFITeagle;;
    *) usage
esac
