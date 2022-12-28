class Comment 

//keyword class e identifier. Identifier deve essere maiuscolo 
class User 
val user1 = new User 

//se non viene definito un costruttore, ce n'è uno di default che non prende argomenti.

//con 4 membri: x, y, e i due metodi. Costruttore primario è nella signature!

class Point(var x:Int, var y:Int) {
  
  def move(dx:Int, dy:Int):Unit = {
    x = x + dx
    y = y + dy
  }

  override def toString:String = {
    s"($x, $y)"
  }
}

val point1 = new Point(2, 3)
println(point1.x)
println(point1)


//costruttori possono avere parametri opzionali usando dei valori di default:

class Point(var x : Int = 0, var y : Int = 0)
val origin = new Point //x, y a 0
val point1 = new Point(1) //x a 1, y a 0 
println(point1)

//se voglio passare il valore della y:
val point2 = new Point(y = 2)

//membri sono pubblici di default. Si possono privatizzare con la keyword private.
class Point {
  //dati memorizzati in _x e _y, ma privati. 
  private var _x = 0
  private var _y = 0
  private val bound = 100

  //metodi x e y per accedere ai dati. Metodi x_= e y_= per settare i dati _x e _y.
  //per i getter si usa l'identificatore con appeso _=
  def x: Int = _x
  def x_=(newValue: Int): Unit = {
    if (newValue < bound)
      _x = newValue
    else
      printWarning()
  }

  def y: Int = _y
  def y_=(newValue: Int): Unit = {
    if (newValue < bound)
      _y = newValue
    else
      printWarning()
  }

  private def printWarning(): Unit =
    println("WARNING: Out of bounds")
}

val point1 = new Point
point1.x = 99
point1.y = 101 // prints the warning


//parametri dei costruttori con val e var sono pubblici. Ma se è val è immutabile, se var mutabile. 
class Point(val x: Int, var y: Int)
val point = new Point(1, 2)
point.x = 3 // error
point.y = 5 // should be ok

//parametri dei costruttori senza val o var sono privati e non visibili all'esterno della classe. 
class Point(x:Int, y:Int) 
val point = new Point(1, 2)
point.x //errore
















