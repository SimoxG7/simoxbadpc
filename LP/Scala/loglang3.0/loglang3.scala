import scala.util.parsing.combinator._ 
import java.io._
import util.Try 
import scala.io.Source 
import scala.collection.mutable._
import java.io.FileOutputStream
import java.io.FileInputStream

class LogLang3() extends JavaTokenParsers {

  def program = rep1(task)

  def task = "task" ~> ident ~ ( "{" ~> rep1(command) <~ "}")

  def command = remove | rename | merge | backup 

  def remove = "remove" ~> unquotedname ^^ { case s => 
    Try(new File(s).delete).getOrElse(false)
  }

  def rename = "rename" ~> unquotedname ~ unquotedname ^^ { case s ~ d => 
    Try(new File(s).renameTo(new File(d))).getOrElse(false)
  }

  def merge = "merge" ~> unquotedname ~ unquotedname ~ unquotedname ^^ { case s1 ~ s2 ~ d => 
    Try(( () => {
      new FileOutputStream(new File(d)).getChannel.transferFrom(
        new FileInputStream(new File(s1)).getChannel,
        0,
        Long.MaxValue
      );
      new FileOutputStream(new File(d), true).getChannel.transferFrom(
        new FileInputStream(new File(s2)).getChannel,
        0,
        Long.MaxValue
      );
      true
    })()).getOrElse(false)
  }

  def backup = "backup" ~> unquotedname ~ unquotedname ^^ { case s ~ d => 
    Try(( () => {
      new FileOutputStream(new File(d)).getChannel.transferFrom(
        new FileInputStream(new File(s)).getChannel,
        0,
        Long.MaxValue
      );
      true
    })()).getOrElse(false)
  }

  def unquotedname = stringLiteral ^^ { case s => s.substring(1, s.length -1) }

}

object LogLang3 {
  def main(args:Array[String]):Unit = {
    val p = new LogLang3
    args.foreach {
      filename => 
        val source = Source.fromFile(filename)
        val lines = source.mkString
        p.parseAll(p.program, lines) match {
          case p.Success(parsed, _) => { 
            parsed.foreach {
              case p.~(taskname, list) => {
                println("Task: " + taskname)
                list.zipWithIndex.foreach {
                  case (result, index) => println("  [Op" + (index+1) + "] -> " + result)   
                }
              } 
            }
          }
          case other => println("Unsuccessful parsing." + other)
        }
    }
  }
}
