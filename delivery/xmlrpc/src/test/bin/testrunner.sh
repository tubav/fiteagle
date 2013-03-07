#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo "Stopping old/hung jetty server..."
mvn jetty:stop
sleep 3

echo "Updating dependencies..."
mvn -U install -DskipTests

echo "Starting jetty server in background..."
mvn jetty:run &
sleep 20

echo "Running tests..."
$DIR/testSFALocalhostGetVersion.sh
code=$?

echo "Stopping jetty server..."
mvn jetty:stop
sleep 3

exit $code
