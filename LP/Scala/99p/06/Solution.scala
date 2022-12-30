object Solution {

  def palindrome[A](lst:List[A]):Boolean = {
    lst == lst.reverse
  }

}