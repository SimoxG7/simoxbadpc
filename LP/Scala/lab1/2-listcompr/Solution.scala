object Solution {
  
  def squared_numbers(lst:List[Any]):List[Int] = {
    for (elem <- lst if (elem.isInstanceOf[Int])) yield ((Int)elem * (Int)elem)
  }

  def intersect(l1:List[Any], l2:List[Any]):List[Any] = {
    for (elem <- l1 if (l1.contains(elem) && l2.contains(elem))) yield elem 
  }

  def symmetric_difference(l1:List[Any], l2:List[Any]):List[Any] = {
    for (elem <- l1:::l2 if (!(l1.contains(elem) && l2.contains(elem)))) yield elem
  }

}