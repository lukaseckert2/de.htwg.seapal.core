de.htwg.seapal.core
===================

Core repository for both, the web and the android project of Seapal.

## How to get the package using SBT ##

1. Add the dependency *"de.htwg.seapal" % "core" % "1.0-SNAPSHOT"*

## How to publish changes ##

1. Install SBT

> http://www.scala-sbt.org/release/docs/Getting-Started/Setup.html (eg. MSI for Windows)

2. Start a console/shell windows

* *sbt* 

> To start sbt console

* *compile* 

> Compile the java files and automaticly loads the dependent libraries

* *package* 

> Creates the **jar** package

> This jar can be used in any project, which is not an SBT project by simply referencing this java archieve in you IDE

* *publish*

> Publishes the **jar** and **pom** files to the artifactory server of HTWG Konstanz