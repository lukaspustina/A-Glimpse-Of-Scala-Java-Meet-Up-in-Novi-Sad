package h

import scala.io.Source

class StackableTraits {

  trait LogProcessor {
    def processLine( line: String )
  }


  class LogProcessorFromFile( val fileName: String ) extends LogProcessor {
    def run(): Unit = {
      val file = Source.fromFile(fileName)
      for ( line <- file.getLines() ) processLine(line)
    }

    override def processLine(line: String): Unit = {
      // write to back end
    }
  }


  val logProcessor = new LogProcessorFromFile("/etc/issue")
  logProcessor.run()

  object StackableTrait {

    trait LogStdOut extends LogProcessor {
      abstract override def processLine(line: String) = {
        println(line)
        super.processLine(line)
      }
    }

    val logProcessorWithStdOut = new LogProcessorFromFile("/etc/issue") with LogStdOut with LogCounter
  }
}
