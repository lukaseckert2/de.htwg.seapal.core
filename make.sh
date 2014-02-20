#!/bin/bash
sbt compile && sbt package && mv target/core-1.0-SNAPSHOT.jar ../de.htwg.seapal.play/lib/.
#terminal-notifier -message "command ended with $?"
#cd ../Seapal-Web/setupDB
#./setupDB.sh
