object Solution {

  def aux[A](lst:List[A], cont:Int):Int = {
    lst match {
      case _::t => aux(t, cont+1)
      case Nil => cont
    }
  }

  def length[A](lst:List[A]):Int = {
    aux(lst, 0)
  }

  def main(args:Array[String]):Unit = {
    println(length(List(1,2,3,4,5)))
  }
}