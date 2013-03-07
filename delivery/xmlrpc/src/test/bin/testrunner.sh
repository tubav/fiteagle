#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo "Updating dependencies..."
mvn -U -B install -DskipTests

echo "Stopping old/hung jetty server..."
mvn -B jetty:stop

echo "Installing certificates..."
./src/main/bin/ssl_create_server_cert.sh 
#./src/main/bin/ssl_add_user_cert.sh to_be_defined testuser

echo "Starting jetty server in background..."
mvn -B jetty:run &
sleep 20

echo "Running tests..."
$DIR/testSFALocalhostGetVersion.sh
code=$?

echo "Stopping jetty server..."
mvn -B jetty:stop

exit $code
