import scala.util.parsing.combinator._

class CalculatorV2 extends JavaTokenParsers {

  def expr: Parser[Double] = term ~ rep(lowprioop ~ term) ^^ {
    case init ~ lst => {
      var res = init
      for ((op, value) <- lst) {
        res = op(res, value)
      }    
      res 
    }
  }

  def op = lowprioop | highprioop

  def lowprioop = ("+" | "-") ^^ {
    case "+" => { val add: (Double, Double) => Double = _ + _; add }
    case "-" => { val sub: (Double, Double) => Double = _ - _; sub }
  }

  def term = factor ~ rep(highprioop ~ factor)

  def highprioop = ("*" | "/") ^^ {
    case "*" => { val mul: (Double, Double) => Double = _ * _; mul }
    case "/" => { val div: (Double, Double) => Double = _ / _; div }
  }

  def factor: Parser[Double] = floatingPointNumber | ("(" ~ expr ~ ")")
}

object CalculatorV2 {
  def main(args: Array[String]): Unit = {
    val input = "2 * (3 + 7)"
    val c = new CalculatorV2
    println(c.parseAll(c.expr, input))
  }
}
