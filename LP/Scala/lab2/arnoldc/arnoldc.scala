import scala.util.parsing.combinator._

class ArnoldC extends JavaTokenParsers {

  def arnoldmain = "IT'S SHOW TIME" ~> mainbody <~ "YOU HAVE BEEN TERMINATED"

  def printer = "TALK TO THE HAND" ~> stringLiteral 
}



