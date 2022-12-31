object Solution {

  def gcd(n1:Int, n2:Int):Int = {
    if (n2 == 0) n1
    else gcd(n2, n1 % n2)  
  }

  def isCoprimeTo(n1:Int, n2:Int):(Boolean, Int) = {
    val gcdval = gcd(n1,n2)
    (gcdval == 1, gcdval) 
  }

  //amount of numbers for which n is coprime with
  def eulerTotient(n:Int):Int = {
    (for (num <- List.range(1, n+1) if (isCoprimeTo(n, num))._1) yield num).length
  }
}