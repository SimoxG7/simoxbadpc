object Solution {

  def isPrime(n:Int):Boolean = {

    def aux(n:Int, curr:Int):Boolean = {
      if (curr >= n) true 
      else {
        n%curr match {
          case 0 => false 
          case _ => aux(n, curr+1)
        }
      } 
    }

    aux(n, 2)
  }

  val n = 100
  for (num <- List.range(2, n) if isPrime(num)) yield num

}