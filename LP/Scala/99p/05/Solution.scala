object Solution {

  def reverse[A](lst:List[A]):List[A] = {
    //lst.reverse
    //o anche con init 
    lst match {
      case Nil => Nil 
      case h::t => reverse(t) ::: List(h)
    }
  }

  def main(args:Array[String]):Unit = {
    println(reverse(List(1,2,3,4,5)))
  }
}