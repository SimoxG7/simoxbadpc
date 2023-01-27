import scala.util.parsing.combinator._
import scala.io.Source
import java.io._
import scala.collection.mutable._
import util.Try
import java.io.FileOutputStream
import java.io.FileInputStream

class LogLang4() extends JavaTokenParsers {

  def program = rep1(task)

  def task = "task" ~> ident ~ ("{" ~> rep1(command) <~ "}")

  def command = remove | rename | merge | backup

  def remove = "remove" ~> unquoted ^^ { case s =>
    Try(new File(s).delete).getOrElse(false)
  }

  def rename = "rename" ~> unquoted ~ unquoted ^^ { case s ~ d =>
    Try(new File(s).renameTo(new File(d))).getOrElse(false)
  }

  def merge = "merge" ~> unquoted ~ unquoted ~ unquoted ^^ { case s1 ~ s2 ~ d =>
    Try((() => {
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

  def backup = "backup" ~> unquoted ~ unquoted ^^ { case s ~ d =>
    Try((() => {
      new FileOutputStream(new File(d)).getChannel.transferFrom(
        new FileInputStream(new File(s)).getChannel,
        0,
        Long.MaxValue
      );
      true
    })()).getOrElse(false)
  }

  def unquoted = stringLiteral ^^ { case s => s.substring(1, s.length - 1) }

}

object LogLang4 {
  def main(args: Array[String]): Unit = {
    val p = new LogLang4
    args.foreach { filename =>
      val source = Source.fromFile(filename)
      val lines = source.mkString
      p.parseAll(p.program, lines) match {
        case p.Success(parsed, _) => {
          parsed.foreach {
            case p.~(name, lst) => {
              println("Task: " + name)
              lst.zipWithIndex.foreach { case (res, index) =>
                println(" [Op" + (index + 1) + "] -> " + res)
              }
            }
          }
        }
        case other => println("Unsuccessful parsing")
      }
    }
  }
}
