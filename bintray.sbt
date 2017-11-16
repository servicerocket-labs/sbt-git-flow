bintrayOrganization in ThisBuild := Some("servicerocket-bintray")

publishArtifact in (Compile, packageDoc) in ThisBuild  := false

bintrayReleaseOnPublish in ThisBuild := false
