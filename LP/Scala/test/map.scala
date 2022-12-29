import scala.collection.mutable.ListBuffer
object Map {

  def addOne(n:Int):Int = n+1

  var list = new ListBuffer[Int]
  list += 1
  list += 2 
  list += 3
  var newlist = list.foreach(num => addOne(num))

}