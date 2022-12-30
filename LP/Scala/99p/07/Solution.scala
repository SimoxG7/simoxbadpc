object Solution {

  // def flatten(lst:List[Any]):List[Any] = {
  //   lst match {
  //     case Nil => Nil 
  //     case h::t => h match {
  //         case hs::ts => hs :: flatten(ts) ::: flatten(t)
  //         case _ => h :: flatten(t) 
  //       }
  //   }
  // }
  
  def flatten(lst:List[Any]):List[Any] = {
    lst flatMap {
      case ms:List[_] => flatten(ms)
      case el => List(el)
    }
  }

  flatten(List(1,List(2,3),List(List(4,5,6), List(7,8,9))))
}