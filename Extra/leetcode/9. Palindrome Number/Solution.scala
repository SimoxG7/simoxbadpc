object Solution {
  def isPalindrome(x: Int): Boolean = {
    x.toString().equals(x.toString().reverse)  
  }
}