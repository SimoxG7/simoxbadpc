import scala.util.parsing.combinator._

class Arith extends JavaTokenParsers {
  def expr: Parser[Any] = term~rep("+"~term | "-"~term)
  def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)
  def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
}

object ParseExpr extends Arith {
  def main(args: Array[String]) {
    println("input : "+ args(0))
    println(parseAll(expr, args(0)))
  }
}


//scala ParseExpr "2 * (3 + 7)" -> ok
//scala ParseExpr "2 * (3 + 7))" -> errore

