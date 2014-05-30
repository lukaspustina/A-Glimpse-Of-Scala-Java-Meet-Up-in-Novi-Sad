package g

import java.io.File

object NewKeywords {

  val file: File = log("Open file") (
    new File("/etc/services")
  )

  def log[T]( taskName: String )( f: => T ): T = {
    println("Starting '%s' ..." format taskName)
    val result = f
    println("Finished '%s'." format taskName)
    result
  }


}
