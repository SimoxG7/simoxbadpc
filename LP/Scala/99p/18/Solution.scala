object Solution {
  
  def slice[A](start:Int, end:Int, lst:List[A]):List[A] = {
    
    (start, end, lst) match {
      case (_, _, Nil) => Nil
      case (_, 0, _) => Nil 
      case (0, e, h::t) => h::slice(0, e-1, t)
      case (s, e, h::t) => slice(s-1, e-1, t)
    }
  }

  //lst.slice(start, end)

}