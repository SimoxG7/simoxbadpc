import scala.util.parsing.combinator._
import java.io._
import util.Try
import scala.io.Source

class LogLang2 extends JavaTokenParsers {

  def program = rep1(task)

  def task = "task" ~> ident ~ ("{" ~> rep1(command) <~ "}")

  def command = (remove | rename | merge | backup)

  def filename = stringLiteral ^^ { case s => s.substring(1, s.length - 1) }

  def remove = "remove" ~> filename ^^ { case s =>
    Try(new File(s).delete).getOrElse(false)
  }

  def rename = "rename" ~> filename ~ filename ^^ { case s ~ d =>
    Try(new File(s).renameTo(new File(d))).getOrElse(false)
  }

  def merge = "merge" ~> filename ~ filename ~ filename ^^ { case s1 ~ s2 ~ d =>
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

  def backup = "backup" ~> filename ~ filename ^^ { case s ~ d =>
    Try(( () => {
      new FileOutputStream(new File(d)).getChannel.transferFrom(
        new FileInputStream(new File(s)).getChannel,
        0,
        Long.MaxValue
      );
      true
    })()).getOrElse(false)
  }

}

object LogLang2 {
  def main(args: Array[String]): Unit = {
    val p = new LogLang2
    args.foreach { filename =>
      val source = Source.fromFile(filename)
      val lines = source.mkString
      p.parseAll(p.program, lines) match {
        case p.Success(l, _) => {
          println("Successful parsing")
          l.foreach { task =>
            task match {
              case p.~(name, ls) => {
                println("Task: " + name);
                ls.zipWithIndex.foreach{ case (e, i) => println("  op" + (i+1) + ": " + e) }
              }
            }
          }
        }
        case x => println("Unsuccessful parsing" + x)
      }
      source.close()
    }
  }
}
