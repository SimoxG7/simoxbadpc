object Solution {

  def insertAt[A](elem:A, n:Int, lst:List[A]):List[A] = {
    val (l1, l2) = lst.splitAt(n)
    l1:::(elem::l2)
  }
}
