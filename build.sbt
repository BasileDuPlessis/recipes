name         := "recipes"
version      := "1.0"
scalaVersion := "2.11.8"

val akkaVersion  = "2.4.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % "test"
libraryDependencies ++= Seq(
  "com.sksamuel.elastic4s" %% "elastic4s-streams" % "2.3.0",
  "com.sksamuel.elastic4s" %% "elastic4s-testkit" % "2.3.0",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
  "org.slf4j" % "log4j-over-slf4j" % "1.7.21"
)


enablePlugins(DockerPlugin)

dockerAutoPackageJavaApplication()