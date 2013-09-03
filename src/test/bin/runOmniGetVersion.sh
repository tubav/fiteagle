#!/bin/bash

type omni.py >/dev/null 2>&1 || { echo >&2 "I require omni.py but it's not installed.  Aborting."; exit 1; }

omni.py -V3 -c ./src/test/resources/omni_config getversion 2>&1
