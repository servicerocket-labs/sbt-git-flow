resolvers += "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")