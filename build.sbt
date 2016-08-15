name := """minimal-site"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

libraryDependencies += "org.webjars" % "bootstrap" % "3.3.7"
libraryDependencies += "org.webjars" % "requirejs" % "2.2.0" 
libraryDependencies += "org.webjars" % "jquery" % "3.1.0" 

