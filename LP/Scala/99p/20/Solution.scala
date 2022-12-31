object Solution {

  def removeAt[A](n:Int, lst:List[A]):(List[A], A) = {
    var (l1, h::t) = lst.splitAt(n)
    (l1:::t, h)
  }
}