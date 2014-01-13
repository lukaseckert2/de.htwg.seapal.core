#!/bin/bash
sbt compile && sbt package && mv target/core-1.0-SNAPSHOT.jar ../Seapal-Web/lib/.
terminal-notifier -message "command ended with $?"
cd ../Seapal-Web/setupDB
./setupDB.sh
