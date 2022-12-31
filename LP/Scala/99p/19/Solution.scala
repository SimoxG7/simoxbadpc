object Solution {

  def rotate[A](n:Int, lst:List[A]):List[A] = {
    var index = n
    if (n < 0) index = lst.length + n
    val (l1, l2) = lst.splitAt(index)
    l2:::l1
  }
}