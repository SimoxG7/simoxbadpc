object Solution {

  def gcd(n1:Int, n2:Int):Int = {
    if (n2 == 0) n1 
    else gcd(n2, n1 % n2)
  }

}