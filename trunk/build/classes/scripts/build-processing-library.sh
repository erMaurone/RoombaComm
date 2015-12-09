#!/bin/sh
#
# Builds the distribution zip file that contains a properly formatted 
# Processing library.  Unzip the resulting file in the 'libraries' directory
# in Processing to install.
#
# Before running this script, run 'build-jar.sh'
#

pushd packaging
rm -rf processing
mkdir -p                  processing/com.hackingroomba.roombacomm/library
cp ../com.hackingroomba.roombacomm.jar      processing/com.hackingroomba.roombacomm/library/com.hackingroomba.roombacomm.jar
cp processing-export.txt  processing/com.hackingroomba.roombacomm/library/export.txt
cp ../rxtxlib/*           processing/com.hackingroomba.roombacomm/library
cp -r ../processing-jargs.examples processing/com.hackingroomba.roombacomm/jargs.examples
cp ../README              processing/com.hackingroomba.roombacomm/README
cp processing-readme.txt  processing/com.hackingroomba.roombacomm/README-processing.txt
pushd processing
zip -r com.hackingroomba.roombacomm-processing.zip com.hackingroomba.roombacomm
cp com.hackingroomba.roombacomm-processing.zip ../..
popd
popd
