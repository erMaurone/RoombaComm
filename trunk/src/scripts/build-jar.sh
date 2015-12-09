#!/bin/sh
#
# Builds the jar file for the core RoombaComm classes
# This core is used for all direct-connect (serial,etc) cases
# 
# Before running this script, compile all the .java files to .class files
# and "cd build"
#
#

base=`dirname "$0"`

echo "buidling com.hackingroomba.roombacomm.jar..."
jar -cfm com.hackingroomba.roombacomm.jar packaging/Manifest.txt com.hackingroomba.roombacomm
echo "done"
