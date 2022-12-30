object Solution {

  def duplicate[A](lst:List[A]):List[A] = {
    lst match {
      case t::Nil => t::t::Nil
      case h::t => h::h::duplicate(t)
      case Nil => throw new NoSuchElementException 
    }
  }

  def duplicate[A](lst:List[A]):List[A] = {
    lst.map(e => List(e, e)).flatten
    //lst.flatMap(e => List(e, e))
  }
}