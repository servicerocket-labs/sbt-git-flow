package com.servicerocket.sbt.release.git.flow

import com.servicerocket.sbt.release.git.flow.Steps._

import sbt._
import sbtrelease._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.autoImport.{ReleaseStep, releaseProcess}

/** SBT auto plugin making the release process integrated with git-flow.
  *
  * @author Nader Hadji Ghanbari
  */
object SbtGitFlowReleasePlugin extends AutoPlugin {

  private val releaseSteps = Seq[ReleaseStep](
      checkSnapshotDependencies,
      checkGitFlowExists,
      inquireVersions,
      runTest,
      gitFlowReleaseStart,
      setReleaseVersion,
      commitReleaseVersion,
      publishArtifacts,
      gitFlowReleaseFinish,
      pushMaster,
      setNextVersion,
      commitNextVersion,
      pushChanges
    )

  override def requires = ReleasePlugin

  override def trigger = allRequirements

  override lazy val projectSettings = Seq[Setting[_]](releaseProcess := releaseSteps)

}
