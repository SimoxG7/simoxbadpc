object Solution {

  def compress[A](lst:List[A]):List[A] = {
    lst match {
      case Nil => Nil
      case h::t => h::compress(t.dropWhile(_ == h))  
    }
  }
}