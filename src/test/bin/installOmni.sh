#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"
_version="gcf-2.4"
_url="http://software.geni.net/local-sw/real_download"
_data="software=${_version}.tar.gz&accept=I+have+read+and+accept+the+GPO+terms+of+service+and+disclaimer"

require curl tar

echo -n "Downloading..."
mkdir -p target
curl -s --data ${_data} ${_url}|tar xz -C target
echo "done"

echo "Now please update your environment:"
echo "  export OMNI_HOME=$(pwd)/target/${_version}"
echo "  export PATH=\$OMNI_HOME/src:\$PATH"
