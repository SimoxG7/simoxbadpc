object Solution {
  
  def nth[A](n:Int, lst:List[A]):A = {
    (n, lst) match {
      case (0, h::_) => h 
      case (n, _::t) => nth(n-1, t) 
      case (_, Nil) => throw new NoSuchElementException
    }
  }

  //List(1,2,3,4,5)(2)
}