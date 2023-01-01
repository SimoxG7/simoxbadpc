object Solution {

  def primeFactorMultiplicity(n:Int):List[(Int, Int)] = {

    def aux(n:Int, div:Int, acc:List[(Int, Int)], count:Int):List[(Int, Int)] = {
      (n, n%div, count) match {
        case (1, _, _) => acc:::List((div, count))
        case (_, 0, _) => aux(n/div, div, acc, count+1)
        case (_, _, 0) => aux(n, div+1, acc, 0)
        case (_, _, _) => aux(n, div+1, acc:::List((div, count)), 0) 
      }
    }
    
    aux(n, 2, List(), 0)
  }
}