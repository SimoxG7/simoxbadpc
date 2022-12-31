object Solution {


  def flatMapSublists[A, B](ls: List[A])(f: (List[A]) => List[B]): List[B] = {
    ls match {
      case Nil => Nil
      case sublist @ (_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
    }
  }

  def combinations[A](n: Int, ls: List[A]): List[List[A]] = {
    n match {
      case 0 => List(Nil)
      case _ => flatMapSublists(ls) { sl =>
        combinations(n - 1, sl.tail) map { sl.head :: _ }
      }
    }
  }
}
