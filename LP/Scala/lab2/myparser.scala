import scala.util.parsing.combinator._

class MyParser extends JavaTokenParsers {

  def doc:Parser[Any] = repsep(line, ",")

  def line:Parser[Any] = "hello" ~> "dude" ~> ident ^^ { str:String => println(str.reverse) }

  //def name:Parser[Any] = stringLiteral ^^ { _.toString } 
}

object MyParser {
  def main(args:Array[String]):Unit = {
    val input = "hello dude Simox, hello dude Tea"
    val mp = new MyParser
    println(mp.parseAll(mp.doc, input))

  }
}

