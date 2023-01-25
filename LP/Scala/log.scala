import scala.util.parsing.combinator._
import scala.collection.mutable._
import java.io.{File, FileInputStream, FileOutputStream}
import scala.language.postfixOps
import util.Try

class logParser() extends JavaTokenParsers {

  def logProgram = rep("task" ~> ident ~ ("{" ~> ops <~ "}"))
  def nameTask = stringLiteral

  def ops = op ~ rep(op)
  def op = remove | rename | backup | merge

  def remove = "remove" ~> unquoted ^^ { case s =>
    Try(new File(s).delete()).getOrElse(false)
  }
  def rename = "rename" ~> unquoted ~ unquoted ^^ { case f ~ a =>
    Try(new File(f).renameTo(new File(a))).getOrElse(false)
  }

  def merge = "merge" ~> unquoted ~ unquoted ~ unquoted ^^ {
    case s1 ~ s2 ~ s3 =>
      Try((() => {
        new FileOutputStream(new File(s3))
          .getChannel() transferFrom (new FileInputStream(
          new File(s1)
        ) getChannel, 0, Long.MaxValue);
        new FileOutputStream(new File(s3), true).getChannel() transferFrom (
          new FileInputStream(new File(s2)) getChannel, 0, Long.MaxValue
        ); true
      })()).getOrElse(false)
  }

  def backup = "backup" ~> unquoted ~ unquoted ^^ { case s ~ t =>
    Try((() => {
      new FileOutputStream(new File(t)).getChannel() transferFrom (
        new FileInputStream(new File(s)) getChannel, 0, Long.MaxValue
      ); true
    })()).getOrElse(false)
  }

  def unquoted = stringLiteral ^^ { case s => s.substring(1, s.length - 1) }

}

object logLangParser {

  def main(args: Array[String]): Unit = {
    val p = new logParser()

    args.foreach { filename =>
      val src = scala.io.Source.fromFile(filename)
      val lines = src.mkString

      p.parseAll(p.logProgram, lines) match {
        case p.Success(s, _) => println("ok!")
        /*s.foreach{
            _ match {
                case p.~(s1,l) => {
                    println("Task" + s1);
                    l.zipWithIndex.foreach{ case(e,i) => println(" [op]"+ (1+i)+ "]"+ e)}
                }
            }

        }*/
        case x => print(x.toString)
      }
      src.close()
    }

  }
}
