#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require omni.py

omni.py -V3 -c ${_dir}/../resources/omni_config getversion 2>&1
