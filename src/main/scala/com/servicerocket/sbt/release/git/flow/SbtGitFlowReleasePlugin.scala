package com.servicerocket.sbt.release.git.flow

import com.servicerocket.sbt.release.git.flow.Steps._

import sbt._
import sbt.State._
import sbtrelease._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleasePlugin.autoImport.{ReleaseStep, releaseProcess}


/** SBT auto plugin making the release process integrated with git-flow.
  *
  * @author Nader Hadji Ghanbari
  */
object SbtGitFlowReleasePlugin extends AutoPlugin {

  private def removeStackTraces(rs: ReleaseStep): ReleaseStep =
    ReleaseStep(
      action = rs.action,
      check = (state: State) => {
        try {
          state.next match {
            case _: Return => state
            case _ => rs.check(state)
          }
        } catch {
          case err: Throwable =>
            throw sbtgitflow.SbtGitFlowException(err.getMessage)
        }
      },
      enableCrossBuild = rs.enableCrossBuild
    )

  private val releaseSteps = Seq[ReleaseStep](
      removeStackTraces(checkSnapshotDependencies),
      removeStackTraces(checkGitFlowExists),
      removeStackTraces(inquireVersions),
      removeStackTraces(runTest),
      removeStackTraces(gitFlowReleaseStart),
      removeStackTraces(setReleaseVersion),
      removeStackTraces(commitReleaseVersion),
      removeStackTraces(publishArtifacts),
      removeStackTraces(gitFlowReleaseFinish),
      removeStackTraces(pushMaster),
      removeStackTraces(setNextVersion),
      removeStackTraces(commitNextVersion),
      removeStackTraces(pushChanges)
    )

  override def requires = ReleasePlugin

  override def trigger = allRequirements

  override lazy val projectSettings = Seq[Setting[_]](releaseProcess := releaseSteps)

}
