object Solution {

  def pack[A](lst:List[A]):List[List[A]] = {
    val (packed, next) = lst.span(_ == lst.head)
    next match {
      case Nil => List(packed)
      case _ => packed :: pack(next)
    }
  }

  def encode[A](lst:List[A]):List[(Int, A)] = {
    pack(lst).map(e => (e.length, e.head))
  }

}