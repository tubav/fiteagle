#!/bin/bash

_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source "${_dir}/run.config.sh"

require omni.py

export CFG="${_dir}/../resources/omni_config"
export RSPC="${_dir}/../resources/omni_resource.rspec"
export logdir="/tmp"

tests=(
"Test.test_GetVersion"
"Test.test_ListResources"
"Test.test_ListResources_geni_compressed"
"Test.test_ListResources_geni_available"
"Test.test_ListResources_badCredential_malformedXML"
"Test.test_ListResources_badCredential_alteredObject"
"Test.test_ListResources_badCredential_badtype"
"Test.test_ListResources_untrustedCredential"
"Test.test_CreateSliver"
"Test.test_CreateSliverWorkflow_fail_notexist"
"Test.test_CreateSliverWorkflow_multiSlice"
"Test.test_CreateSliver_badrspec_emptyfile"
"Test.test_CreateSliver_badrspec_malformed"
"Test.test_CreateSliver_badrspec_manifest"
)

function runTest {
  cd $OMNI_HOME/acceptance_tests/AM_API/
  export PYTHONPATH=$OMNI_HOME/src
  ./am_api_accept.py -V3 -c "${CFG}" --rspec-file "${RSPC}" $1 > "${logdir}/$1.log" 2>&1
  return $?
}

echo "Log files at: $logdir/Test.test_*";

for test in "${tests[@]}"; do
    echo -n "$test: "
    runTest $test
    echo "$?"
done
