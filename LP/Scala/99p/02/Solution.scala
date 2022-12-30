object Solution {
  
  def penultimate[A](lst: List[A]): A = {
    lst match {
      case pen :: _ :: Nil => pen  
      case _ :: tail => penultimate(tail) 
      case Nil => throw new NoSuchElementException
    }
  }

  penultimate(List(1,2,3,4,5))
}
