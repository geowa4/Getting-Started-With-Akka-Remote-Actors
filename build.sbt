name := "Akka Remote Actors"

version := "1.0"

scalaVersion := "2.9.1"

resolvers += "Typesafe Repository" at 
	    "http://repo.typesafe.com/typesafe/releases" 

libraryDependencies ++= Seq(
		    "se.scalablesolutions.akka" % "akka-actor" % "1.1.3",
		    "se.scalablesolutions.akka" % "akka-remote" % "1.1.3"
)