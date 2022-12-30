object Solution {
  
  def split[A](n:Int, lst:List[A]):(List[A], List[A]) = {
    
    (n, lst) match {
      case (_, Nil) => (Nil, Nil)
      case (0, l) => (Nil, l) 
      case (n, h::t) => {
        val (pre, post) = split(n-1, t)
        (h::pre, post)
      }
    }
    
    //list.splitAt(n)
  }

}