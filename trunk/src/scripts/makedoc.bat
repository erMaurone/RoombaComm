REM javadoc -d html -classpath $MYCP ../rxtx/src/*.java -exclude gnu.io com.hackingroomba.roombacomm
javadoc -d html -classpath .:./rxtxlib/RXTXcomm.jar com.hackingroomba.roombacomm/*.java
