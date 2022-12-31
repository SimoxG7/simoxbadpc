object Solution {

  def factors(n:Int):List[Int] = {

    def aux(n:Int, div:Int):List[Int] = {
      (n, n%div) match {
        case (1, _) => Nil
        case (_, 0) => List(div):::aux(n/div, div)
        case (_, _) => aux(n, div+1) 
      }
    }

    aux(n, 2)
  }
}