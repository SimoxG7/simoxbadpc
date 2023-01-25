import scala.util.parsing.combinator._

class JSON extends JavaTokenParsers {
  
  def value:Parser[Any] = obj | arr | stringLiteral | floatingPointNumber | "null" | "false" | "true"
  
  def obj:Parser[Any] = "{" ~ repsep(member, ",") ~ "}"

  def member:Parser[Any] = stringLiteral ~ ":" ~ value

  def arr:Parser[Any] = "[" ~ repsep(value, ",") ~ "]"
}

object JSON {
  def main(args: Array[String]): Unit = {

    val input = """
    {
    "address book": {
      "name": "John Smith",
      "address": {
        "street": "10 Market Street",
        "city" : "San Francisco, CA",
        "zip" : 94111
      },
      "phone numbers": [
        "408 338-4238",
        "408 111-6892"
      ]
      }
    }
    """

    //val reader = new FileReader(filename)
    //println(j.parseAll(j.value, reader))
    val j: JSON = new JSON
    println(j.parseAll(j.value, input))

  }
}
