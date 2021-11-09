name := "pro"

version := "0.1"

scalaVersion := "2.13.6"



libraryDependencies += "org.typelevel" %% "cats-effect" % "2.2.0"


libraryDependencies += "org.scalanlp" %% "breeze-viz" % "1.1"

libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"
// Scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test"


scalacOptions ++= Seq("-language:postfixOps", "-language:implicitConversions")

