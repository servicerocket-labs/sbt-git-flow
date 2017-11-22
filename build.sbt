name := "sbt-git-flow"

organization := "com.servicerocket"
organizationName := "ServiceRocket"
organizationHomepage := Option(url("http://www.servicerocket.com"))
bintrayOrganization := Some("servicerocket-bintray")

description := "SBT plugin for git-flow integrated release"
startYear := Option(2015)
licenses +=("MIT", url("http://opensource.org/licenses/MIT"))

scalaVersion := "2.10.5"

sbtPlugin := true

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.3")
