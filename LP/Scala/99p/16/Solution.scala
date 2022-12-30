object Solution {
  
  def dropNth[A](n:Int, lst:List[A]):List[A] = {
    def aux[A](n:Int, lst:List[A], cont:Int):List[A] = {
      lst match {
        case h::t => cont match {
          case 0 => aux(n, t, 1)
          case other => h::aux(n, t, (other+1)%n)
        }
        case Nil => Nil
      }
      //aux(n, t, (cont+1) % n)
    }
    aux(n, lst, 1)
  }

  drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

}