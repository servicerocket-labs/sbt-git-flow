name := "sbt-git-flow"

organization := "com.servicerocket"
organizationName := "ServiceRocket"
organizationHomepage := Option(url("http://www.servicerocket.com"))

description := "SBT plugin for git-flow integrated release"
startYear := Option(2015)
licenses +=("MIT", url("http://opensource.org/licenses/MIT"))

scalaVersion := "2.10.7"

sbtPlugin := true

crossSbtVersions := Seq("0.13.16", "1.0.3")


libraryDependencies += {
  def verPrefix(value: String) =
    VersionNumber(value).numbers.take(2).mkString(".")

  val sbtVer = verPrefix((sbtVersion in pluginCrossBuild).value)
  val scalaVer = verPrefix((scalaVersion in pluginCrossBuild).value)

  Defaults.sbtPluginExtra("com.github.gseitz" % "sbt-release" % "1.0.6", sbtVer, scalaVer)
}
