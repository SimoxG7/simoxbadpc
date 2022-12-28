object Fact {
  def fact(x:Int):Int = {
    if (x <= 1) 1
    else x * fact(x-1)
  }

  def main(args:Array[String]) = {
    println(fact(4))
  }
}


