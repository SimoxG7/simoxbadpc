object Solution {
  //last

  def last[A](lst:List[A]):A = {
    lst match {
      case last :: Nil => last
      case _ :: next => last(next)
      case _ => throw new NoSuchElementException 
    }
  }
}


