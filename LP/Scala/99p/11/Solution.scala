object Solution {

  def pack[A](lst:List[A]):List[List[A]] = {
    val (packed, next) = lst.span(_ == lst.head)
    next match {
      case Nil => List(packed)
      case _ => packed :: pack(next)
    }
  }

  def modEncode[A](lst:List[A]):List[Any] = {
    pack(lst).map(
      e => e match {
        case h::Nil => h 
        case h::_ => (e.length, h)
        case Nil => throw new NoSuchElementException
      }
    )
  }

}