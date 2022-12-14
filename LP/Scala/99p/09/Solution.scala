object Solution {
  
  def pack(lst:List[Any]):List[Any] = {
    val (packed, next) = lst.span(_ == lst.head)
    next match {
      case Nil => List(packed)
      case _ => packed :: pack(next)
    }
  }

  List(1,1,1,1,2,2,2,2,3,2,2,2,3,1)

}