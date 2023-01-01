object Solution {

  def isPrime(n:Int):Boolean = {
    (for (num <- List.range(2, n-1) if (n % num == 0)) yield num).length == 0
  }

  def primesRange(start:Int, end:Int):List[Int] = {
    for (num <- List.range(start, end+1) if (isPrime(num))) yield num
  }

}