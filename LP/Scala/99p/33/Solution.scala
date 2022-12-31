object Solution {

  def gcd(n1:Int, n2:Int):Int = {
    if (n2 == 0) n1
    else gcd(n2, n1 % n2)  
  }

  def isCoprimeTo(n1:Int, n2:Int):(Boolean, Int) = {
    val gcdval = gcd(n1,n2)
    (gcdval == 1, gcdval) 
  }

}