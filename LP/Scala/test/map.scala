import scala.collection.mutable.ListBuffer
object Map {

  def addOne(n:Int):Int = n+1

  def map[A, B](f:A => B, start:List[A]):List[B] = {
    for (elem <- start) yield f(elem)
  } 

  var newlist:List[Int] = map(((num:Int) => num * 2), List(1,2,3)) 

  var list = List.range(1,10)

  var newlist = list.map((num:Int) => num * num)

}