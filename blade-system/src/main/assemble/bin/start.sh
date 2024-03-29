#!/bin/sh

#check JAVA_HOME & java
if [ -z "$JAVA_HOME" ] ; then
    JAVA_HOME=/usr/local/plugins/jdk1.8.0_201
fi

BASE_HOME=/home/blade/blade-system

#==============================================================================
#set JAVA_OPTS
JAVA_OPTS="-server -Xms256m -Xmx256m -Xmn128m -Xss256k"

#performance Options
JAVA_OPTS="$JAVA_OPTS -XX:+AggressiveOpts"
JAVA_OPTS="$JAVA_OPTS -XX:+UseBiasedLocking"
JAVA_OPTS="$JAVA_OPTS -XX:+UseFastAccessorMethods"
JAVA_OPTS="$JAVA_OPTS -XX:+DisableExplicitGC"
JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC"
JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC"
JAVA_OPTS="$JAVA_OPTS -XX:+CMSParallelRemarkEnabled"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseCMSCompactAtFullCollection"
JAVA_OPTS="$JAVA_OPTS -XX:+UseCMSInitiatingOccupancyOnly"
JAVA_OPTS="$JAVA_OPTS -XX:CMSInitiatingOccupancyFraction=75"

#GC Log Options
#JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCApplicationStoppedTime"
#JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCTimeStamps"
#JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails"
#debug Options
#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=8065,server=y,suspend=n"
#==============================================================================

TEMP_CLASSPATH="$BASE_HOME/conf"
for i in "$BASE_HOME"/lib/*.jar
do
    TEMP_CLASSPATH="$TEMP_CLASSPATH:$i"
done
#==============================================================================
#startup server
RUN_CMD="$JAVA_HOME/bin/java"
RUN_CMD="$RUN_CMD -classpath $TEMP_CLASSPATH"
RUN_CMD="$RUN_CMD $JAVA_OPTS"
RUN_CMD="$RUN_CMD com.blade.manager.system.ManagerSystemApplication >>/dev/null 2>&1 &"

echo $RUN_CMD

eval $RUN_CMD

echo "blade-system server start !"
#==============================================================================