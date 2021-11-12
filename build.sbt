name := "pro"

version := "0.1"

scalaVersion := "2.13.7"

libraryDependencies += "org.typelevel" %% "cats-effect" % "2.2.0"

libraryDependencies += "org.scalanlp" %% "breeze-viz" % "1.1"

libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"
libraryDependencies += "org.scalatest"          %% "scalatest"                  % "3.2.0" % "test"
libraryDependencies += "org.scalatest"          %% "scalatest"                  % "3.0.5" % Test

scalacOptions ++= Seq("-language:postfixOps", "-language:implicitConversions")
