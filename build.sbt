name := """play-2-javaH"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += ws

resolvers += "json" at "https://raw.githubusercontent.com/mathieuancelin/json-lib-javaslang/master/repository/releases"

libraryDependencies += "org.reactivecouchbase" % "json-lib-javaslang" % "2.0.0"