organization  := "winbits"

version       := "1.0.0-SNAPSHOT"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  val akkaVersion = "2.1.4"
  val sprayVersion = "1.1.0"
  Seq(
    "io.spray"            %   "spray-can"     % sprayVersion,
    "io.spray"            %   "spray-routing" % sprayVersion,
    "io.spray"            %   "spray-testkit" % sprayVersion,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaVersion,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaVersion,
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test",
    "com.typesafe.slick"   %% "slick"             % "1.0.1",
    "com.github.tototoshi" %% "slick-joda-mapper" % "0.3.0",
    "mysql" % "mysql-connector-java" % "5.1.25"
  )
}

seq(Revolver.settings: _*)

