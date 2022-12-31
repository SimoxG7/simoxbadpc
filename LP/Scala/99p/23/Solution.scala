import java.util.Random
object Solution {

  def randomSelect[A](n:Int, lst:List[A]):List[A] = {

    def removeAt[A](n:Int, lst:List[A]):(List[A], A) = {
      var (l1, h::t) = lst.splitAt(n)
      (l1:::t, h)
    }

    def aux[A](n:Int, lst:List[A], acc:List[A]):List[A] = {
      n match {
        case 0 => acc
        case _ => {
          val random = new scala.util.Random
          val (l, el) = removeAt(random.nextInt(lst.length), lst)
          aux(n-1, l, acc:::List(el))
        }
      }
    }

    aux(n, lst, List())
  }

}