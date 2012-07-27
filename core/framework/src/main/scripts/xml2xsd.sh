#!/bin/bash

trang_version="trang-20030619"
trang_file_ext=".zip"
trang_file="${trang_version}${trang_file_ext}"
trang_url="http://www.thaiopensource.com/download/${trang_file}"

if [ "" == "$2" ]; then
  echo "Usage: $0 <xml input file> <xsd output file>"
  exit 1
fi

if [ ! -d "target" ]; then
  echo "Please start this script from the project root directory."
  exit 1
fi

if [ ! -e "target/trang.jar" ]; then
  cd target
  curl -C - -O "${trang_url}"
  unzip "${trang_file}"
  cp "${trang_version}/trang.jar" .
  cd ..  
fi

java -jar "target/trang.jar" $1 $2