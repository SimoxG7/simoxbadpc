import scala.collection.mutable.ListBuffer

object Prime {

  def isPrime(x:Int):Boolean = {
    val numlist = for (num <- List.range(2, x) if (x % num == 0)) yield num
    numlist.length == 0
  }

  def generatePrimesToN(n:Int):List[Int] = {
    val primelist = for (num <- List.range(2, n+1) if (isPrime(num))) yield num
    primelist
  }

  def firstNPrimes(n:Int):List[Int] = {
    var res = new ListBuffer[Int]
    var num:Int = 2
    while (res.length < n) {
      if (isPrime(num)) res += num
      num += 1
    }
    res.toList
  }

  def main(args:Array[String]) = {
    println(isPrime(4))
  }
}


