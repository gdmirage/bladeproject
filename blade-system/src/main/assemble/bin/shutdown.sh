#!/bin/sh

#check JAVA_HOME & java
if [ -z "$JAVA_HOME" ] ; then
    JAVA_HOME=/usr/local/plugins/jdk1.8.0_201
fi

#==============================================================================
#set JAVA_OPTS
JAVA_OPTS="-Xss256k"
#==============================================================================

#stop Server
$(ps -ef | grep blade-system | grep ManagerSystemApplication | awk '{print $2}' | xargs kill -9 )

echo "Shutdown blade system is done....."
#==============================================================================