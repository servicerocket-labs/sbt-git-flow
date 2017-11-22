package com.servicerocket.sbt.release.git.flow

import sbt._
import sbtrelease.ReleasePlugin.autoImport.ReleaseKeys._

/** Util for running commands and release steps.
  *
  * @author Nader Hadji Ghanbari
  */
object Util {

  /** Returns the current sbt version pf the project or throws exception if no version is set
    * as this is necessary for a proper release.
    *
    * @param state Current sbt state.
    * @return Current version.
    */
  def releaseVersion(state: State) = state.get(versions).map(_._1).getOrElse {
    sys.error("No versions are set! Was this release part executed before inquireVersions?")
  }

  /** Executes a command in a synchronous blocking way and return the exit code.
    *
    * @param command Command to be executed.
    * @return Exit code.
    */
  def exec(command: String) = List("sh", "-c", s"($command)") ! StandardLogger

  /** Executes a step and logs some information about it.
    *
    * @param f Function form current state to a command to be executed to reach the next state.
    * @return Release step.
    */
  def execStep(f: State => String) = { state: State =>
    val command = f(state)
    state.log.info(s"Executing '$command':")
    Util.exec(command)
    state
  }

}

/** Standard process logger piping output stream to console and
  * error stream to system error.
  */
object StandardLogger extends ProcessLogger {
  override def info(s: => String) = println(s)

  override def error(s: => String) = try {
    sys.error(s)
  } catch {
    case re: RuntimeException =>
      val msg  =
        try {
          re.getCause.getMessage
        } catch {
          case _ : Throwable => re.getMessage
        }

      System.err.println(msg)
  }

  override def buffer[T](f: => T) = f
}
