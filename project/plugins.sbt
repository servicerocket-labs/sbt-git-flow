resolvers ++= Seq(Classpaths.sbtPluginReleases, Classpaths.typesafeReleases)
resolvers += "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
addSbtPlugin("com.servicerocket" % "sbt-git-flow" % "0.1.1")