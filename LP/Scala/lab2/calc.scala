import scala.util.parsing.combinator._

class Calculator extends JavaTokenParsers {

  def number = floatingPointNumber ^^ { num => num.toDouble }
  def expr = number ~ rep(rest)
  def operator = ("+" | "-" | "*" | "/") ^^ { op =>
    op match {
      case "+" => { val sum: (Double, Double) => Double = _ + _; sum }
      case "-" => { val sub: (Double, Double) => Double = _ - _; sub }
      case "*" => { val mult: (Double, Double) => Double = _ * _; mult }
      case "/" => { val div: (Double, Double) => Double = _ / _; div }
    }
  }

  def rest = (operator ~ number) ^^ { case f ~ (num: Double) =>
    (f, num)
  }

  def program: Parser[Double] = (number ~ rep(rest)) ^^ {
    case (init:Double) ~ lst => {
      var ris = init
      println("Init: " + init)
      for ((op, num) <- lst) {
        ris = op(ris, num)
        println(op + " -> " + num + " -> " + ris)
      }
      ris
    }
  }
}

object Calculator {
  def main(args: Array[String]):Unit = {
    val input = "2 + 4 - 5 * 4.5 / 2.1 + 10"

    val p = new Calculator
    val res = p.parseAll(p.program, input)
    println(res)
  }
}
