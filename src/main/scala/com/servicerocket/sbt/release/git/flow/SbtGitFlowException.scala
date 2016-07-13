package sbtgitflow

case class SbtGitFlowException(message: String) extends Throwable(message, null) {
  this.setStackTrace(Array[java.lang.StackTraceElement]())
}
