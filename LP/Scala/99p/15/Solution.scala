object Solution {
  
  def duplicateNTimes[A](n:Int, lst:List[A]):List[A] = {
    lst.flatMap(e => List.fill(n)(e))
  }

}