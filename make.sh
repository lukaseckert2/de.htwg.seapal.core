#!/bin/bash
sbt compile && sbt package && mv target/core-1.1-SNAPSHOT.jar ../de.htwg.seapal.play/lib/de.htwg.seapal.core-1.1-SNAPSHOT.jar
#terminal-notifier -message "command ended with $?"
#cd ../Seapal-Web/setupDB
#./setupDB.sh
