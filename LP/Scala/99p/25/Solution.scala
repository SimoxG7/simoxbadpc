object Solution {

  def removeAt[A](n:Int, lst:List[A]):(List[A], A) = {
    var (l1, h::t) = lst.splitAt(n)
    (l1:::t, h)
  }

  def randomPermute[A](lst:List[A]):List[A] = {
    lst match {
      case Nil => Nil 
      case l => {
        val random = new scala.util.Random 
        val (ls, e) = removeAt(random.nextInt(l.length), l)
        List(e):::randomPermute(ls)
      }
    }
  }


}
