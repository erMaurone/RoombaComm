#!/bin/sh

MYCP="`pwd`:`pwd`/rxtxlib/RXTXcomm.jar"

rm -rf html
#javadoc -d html -classpath $MYCP ../rxtx/src/*.java -exclude gnu.io com.hackingroomba.roombacomm
javadoc -d html -classpath $MYCP com.hackingroomba.roombacomm
