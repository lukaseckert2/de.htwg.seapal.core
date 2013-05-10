// group ID
organization := "de.htwg.seapal"

// artifact ID
name := "core"

// version
version := "1.0-SNAPSHOT"

// general project dependencies
libraryDependencies ++= Seq(
	"org.slf4j" % "slf4j-api" % "1.6.4",
	"org.slf4j" % "slf4j-log4j12" % "1.6.4",
	"com.google.inject" % "guice" % "3.0",
	"org.ektorp" % "org.ektorp" % "1.3.0",
	"com.google.android" % "android" % "4.1.1.4",
	"com.google.code.jcouchdb" % "jcouchdb" % "0.11.0-1",
	"junit" % "junit" % "4.10" ,
	"com.novocode" % "junit-interface" % "0.10-M1" % "test->default"
	//"com.couchbase" % "com.couchbase.jtouchdb" % "0.5-SNAPSHOT"
	//"org.daum.extra.android" % "org.daum.extra.android.touchdbektorp" % "1.0.0-SNAPSHOT"
)

EclipseKeys.projectFlavor := EclipseProjectFlavor.Java

// disable using the Scala version in output paths and artifacts
crossPaths := false

// disable parallel execution
parallelExecution in jacoco.Config := false

// setup entry points for sonar code analyzer
pomExtra :=
  <build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <testSourceDirectory>src/test/java</testSourceDirectory>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
      </resource>
    </resources>
  </build>
  
//resolvers += "TouchdbResolver" at "http://maven.kevoree.org/daum/snapshots"

// publishing target
publishTo := Some("HtwgPublishTo" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime())