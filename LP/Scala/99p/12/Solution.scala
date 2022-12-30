object Solution {

  def decode[A](lst:List[(Int, A)]):List[A] = {
    lst match {
      case (num, value)::Nil => List.fill(num)(value)
      case (num, value)::t => List.fill(num)(value):::decode(t)
      case Nil => throw new NoSuchElementException
    }
  }


  decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
}