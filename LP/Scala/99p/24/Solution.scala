object Solution {
  
  def lotto(n:Int, bound:Int):List[Int] = {

    def removeAt[A](n:Int, lst:List[A]):(List[A], A) = {
      var (l1, h::t) = lst.splitAt(n)
      (l1:::t, h)
    }

    def aux(n:Int, lst:List[Int], acc:List[Int]):List[Int] = {
      n match {
        case 0 => acc
        case _ => {
          val random = new scala.util.Random 
          val (l, e) = removeAt(random.nextInt(lst.length), lst)
          aux(n-1, l, acc:::List(e))
        } 
      }
    }

    aux(n, List.range(1, bound+1), List())
  }

}