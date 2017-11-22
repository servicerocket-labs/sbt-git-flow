package com.servicerocket.sbt.release.git.flow

import sbt._
import com.servicerocket.sbt.release.git.flow.Util._

/** Singleton object containing all release steps.
  *
  * Note that the order does not matter here. The actual order is picked up by the `releaseSteps` settings.
  *
  * @author Nader Hadji Ghanbari
  */
object Steps {

  private val devNull = new java.io.ByteArrayOutputStream()

  lazy val checkGitFlowExists = { state: State =>
    ("git help" #> devNull).! match {
      case 0 =>
        "git help -a".!! match {
          case str if str.contains("flow") =>
            ("git flow init -d" #> devNull).! match {
              case 0 => state
              case _ => sys.error("git-flow init failed!")
            }
          case _ => sys.error("git-flow is required for release. See https://github.com/nvie/gitflow for installation instructions.")
        }
      case _ => sys.error("git is required for release.")
    }
  }

  lazy val gitFlowReleaseStart = execStep {
    "git flow release start v" + releaseVersion(_)
  }

  lazy val gitFlowReleaseFinish = execStep { state =>
    val rv = releaseVersion(state)
    val commands = List(
      s"echo 'Releasing v$rv.' > .git/MY_TAGMSG",
      "git config core.editor \"mv .git/MY_TAGMSG\"",
      s"git flow release finish v$rv",
      "git config --unset core.editor"
    )
    commands mkString "; "
  }

  lazy val pushMaster = execStep { _ =>
    "git push origin master"
  }

}
