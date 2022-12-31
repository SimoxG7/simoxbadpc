object Solution {

  def range(start: Int, end: Int): List[Int] = {

    def aux(start: Int, end: Int, acc: List[Int]): List[Int] = {
      if (start == end) acc ::: List(start)
      else aux(start + 1, end, acc ::: List(start))
    }

    aux(start, end, List())
  }
  
}
