import scala.util.parsing.combinator._

class AnotherCalc extends JavaTokenParsers {

  def expr:Parser[Any] = term ~ rep(opterm) ^^ {
    case (value ~ lst) => {
      val res:Int = value
      for((op, v:Int) <- lst) {
        res = op(res, v)
      }
      res
    }
  } 

  def opterm = (("+" | "-" | "*" | "/") ~ term) ^^ {
    (op ~ value:Int) match {
      case ("+" ~ v) => res = res + v
      case ("-" ~ v) => res = res - v
      case ("*" ~ v) => res = res * v
      case ("/" ~ v) => res = res / v
    }
  }

  def term = wholeNumber ^^ { n => n.toInt }
}

object AnotherCalc {
  def main(args:Array[String]):Unit = {
    val input = "2 + 4 - 1 * 2 / 3"
    val ac = new AnotherCalc
    println(ac.parseAll(ac.expr, input))
  }
}