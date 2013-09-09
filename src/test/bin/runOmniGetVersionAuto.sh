#!/usr/bin/expect -f
spawn ./src/test/bin/runOmniGetVersion.sh
expect "Enter PEM pass phrase:"
send "Password\r"
expect "Enter PEM pass phrase:"
send "Password\r"
expect "Enter PEM pass phrase:"
