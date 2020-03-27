name := "slick-async"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.10.0"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.1" // matching Parsley main app's play-slick version
