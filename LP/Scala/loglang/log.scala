import scala.util.parsing.combinator._
import scala.collection.mutable._
import util.Try
import java.io.{File, FileOutputStream, FileInputStream}
import java.io.PrintWriter
import scala.io.Source

/*
  grammatica:
  remove <<filename>>
  rename <<filename.old>> <<filename.new>>
  merge <<file1>> <<file2>> <<file3>> //contenuti di 1 e 2 in 3
  backup <<file1>> <<file2>> copia file1 in file2
  task <<taskname>> { <<operations>> }

 */

class LogLang extends JavaTokenParsers {

  def program = rep1(task)

  def task = "task" ~> ident ~ ("{" ~> rep1(command) <~ "}")

  def command = (remove | rename | merge | backup)

  def remove = "remove" ~> unquoted ^^ { case s =>
    Try(new File(s).delete()).getOrElse(false)
  }

  def rename = "rename" ~> unquoted ~ unquoted ^^ { case s ~ t =>
    Try(new File(s).renameTo(new File(t))).getOrElse(false)
  }

  def merge = "merge" ~> unquoted ~ unquoted ~ unquoted ^^ { case s1 ~ s2 ~ t =>
    Try({ () =>
      new FileOutputStream(new File(t)).getChannel.transferFrom(
        new FileInputStream(new File(s1)).getChannel,
        0,
        Long.MaxValue
      );
      new FileOutputStream(new File(t)).getChannel.transferFrom(
        new FileInputStream(new File(s2)).getChannel,
        0,
        Long.MaxValue
      );
      true
    }).getOrElse(false)
  }

  def backup = "backup" ~> unquoted ~ unquoted ^^ { case s ~ b =>
    Try({ () =>
      new FileOutputStream(new File(b)).getChannel.transferFrom(
        new FileInputStream(new File(s)).getChannel,
        0,
        Long.MaxValue
      );
      true
    }).getOrElse(false)
  }

  def unquoted = stringLiteral ^^ { case s => s.substring(1, s.length - 1) }

}

object LogLang {
  def main(args: Array[String]): Unit = {
    val p = new LogLang()
    args.foreach { filename =>
      val src = scala.io.Source.fromFile(filename)
      val lines = src.mkString
      p.parseAll(p.program, lines) match {
        case p.Success(s, _) =>
          /*
          s.foreach {
            _ match {
              case p.~(s1, l) => {
                println("Task " + s1);
                l.zipWithIndex.foreach { case (e, i) =>
                  println(" [op" + (i + 1) + "] " + e)
                }
              }
            }
          }
          */ 
          println("correct interpretation")
        case x => print(x.toString)
      }
      src.close()
    }
  }
}


/*
object LogLang {
  def main(args:Array[String]):Unit = {

    val input = "task TaskOne { remove \"application.debug.old\" rename \"application.debug\" \"application.debug.old\" } task TaskTwo { backup \"access.error\" \"security.logs\" backup \"system.error\" \"system.logs\" } task TaskThree { merge \"security.logs\" \"system.logs\" \"security+system.logs\" }"

    //val reader = new FileReader(args)
    val ll = new LogLang
    println(ll.parseAll(ll.program, input)) /*match {
      case ll.Success(s, _) => {
        s.foreach {
          _ match {
            case ll.~(s1, l) => {
              println("Task " + s1);
              l.zipWithIndex.foreach {
                case(e,i) => println(" [op"+(i+1)+"] "+e)
              }
            }
          }
        }
      }
    }
 */

  }
}
 */

 /*
object LogLang {
  def main(args: Array[String]): Unit = {
    val p = new LogLang()
    args.foreach { filename =>
      val src = scala.io.Source.fromFile(filename)
      val lines = src.mkString
      p.parseAll(p.program, lines) match {
        case p.Success(s, _) =>
          s.foreach {
            _ match {
              case p.~(s1, l) => {
                println("Task " + s1);
                l.zipWithIndex.foreach { case (e, i) =>
                  println(" [op" + (i + 1) + "] " + e)
                }
              }
            }
          }
        case x => print(x.toString)
      }
      src.close()
    }
  }
}
*/
