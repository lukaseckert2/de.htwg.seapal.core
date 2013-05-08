// group ID
organization := "de.htwg.seapal"

// artifact ID
name := "core"

// version
version := "1.0-SNAPSHOT"

// general project dependencies
libraryDependencies ++= Seq(
	"com.google.inject" % "guice" % "3.0",
	"org.ektorp" % "org.ektorp" % "1.3.0",
	"com.google.android" % "android" % "4.1.1.4",
	"com.couchbase" % "com.couchbase.jtouchdb" % "0.5-SNAPSHOT",
	"org.daum.extra.android" % "org.daum.extra.android.touchdbektorp" % "1.0.0-SNAPSHOT"
)

// disable using the Scala version in output paths and artifacts
crossPaths := false

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

resolvers += "TouchdbResolver" at "http://maven.kevoree.org/daum/snapshots"

// publishing target
publishTo := Some("HtwgPublishTo" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime())

