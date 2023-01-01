object Solution {

  def isPrime(n:Int):Boolean = {
    (for (num <- List.range(2, n-1) if (n % num == 0)) yield num).length == 0
  }

  def primesRange(start:Int, end:Int):List[Int] = {
    for (num <- List.range(start, end+1) if (isPrime(num))) yield num
  }

  def goldbach(n:Int):(Int, Int) = {

    def aux(n:Int, diff:Int):(Int, Int) = {
      (isPrime(n), isPrime(diff)) match {
        case (true, true) => (n, diff)
        case (_, _) => aux(n-1, diff+1)
      }
    }

    n%2==0 match {
      case true => aux(n-2, 2)
      case false => throw new IllegalArgumentException
    }
    
  }
}