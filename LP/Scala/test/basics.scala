val x = 1+2
println(x)

//tipo di valore può essere omesso o esplicitato 
val y: Int = 1+1 //val non riassegnabili

//var sono riassegnabili
var z = 1+1
z = 2+2
var w:Int = 2+2

//blocchi (stampa ultimo valore valutato)
println({
  val x = 1 + 1
  x + 1
})

//funzioni anonime:
(x:Int) => x*x 

//sono assegnabili
val squared = (x:Int) => x*x 
println(squared(1))

val add = (x:Int, y:Int) => x+y 
println(add(2,3))

//no parameters
val noparams = () => println("teaculo")


//metodi: usano keyword def 
def add(x:Int, y:Int):Int = x+y
add(3,2)

//metodi possono prendere più liste di parametri
def addThenMultiply(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier
println(addThenMultiply(1, 2)(3)) 

//o anche non avere liste di parametri 
def wtfisgoingon:String = System.getProperty("user.name")
println("Hello " + name + "!")

//metodo multiline, ultima espressione è il valore ritornato
def getSquareString(input:Double):String = {
  val square = input*input
  square.toString
}
println(getSquareString(6.9))


//creazione classe: keyword class e poi nome e costruttore 
class Greeter(prefix:String, suffix:String) {
  def greet(name:String):Unit = println(prefix + name + suffix)
}

//nuova istanza con new 
val greeter = new Greeter("Hello, ", "!") 
greeter.greet("Scala developer") //printa "Hello, Scala developer!"

//case classes: hanno istanze immutabili e comparate da valore, non reference. Utili per pattern matching 
case class Point(x:Int, y:Int)

//istanziare case class si può fare senza new keyword
val point = Point(1, 2)
val anotherPoint = Point(3, 4)

//comparazione tra due case class
if (point == anotherPoint) {
  println(s"$point and $anotherPoint are the same.")
} else {
  println(s"$point and $anotherPoint are different.")
}

//oggetti: singole istanze di una loro definizione. Definiti con keyword object. è come singleton  
object IdFactory {
  private var counter = 0
  
  def create():Int = {
    counter += 1
    counter 
  }
}

//si può accedere ad un oggetto riferendone il nome
val newId:Int = IdFactory.create()
println(newId) //printa 1

val newerId:Int = IdFactory.create()
println(newerId) //printa 2


//traits: tipi di dati astratti contenenti metodi e campi. In scala una class può estendere solo una singola class, ma può estendere diversi trait. 
trait Greeter {
  def greet(name:String):Unit
}

//trait possono avere implementazione di default 
trait Greeter {
  def greet(name:String):Unit = println("Hello, " + name + "!")
}


//trait estendibili con la extends keyword e overridibili con la override keyword
class DefaultGreeter extends Greeter 

class CustomizableGreeter (prefix:String, postfix:String) extends Greeter {
  override def greet(name:String):Unit = {
    println(prefix + name + postfix)
  }
} 

val greeter = new DefaultGreeter()
greeter.greet("Scala developer") //printa "Hello, Scala developer!"

val customGreeter = new CustomizableGreeter("How are you, ", "?")
customGreeter.greet("Scala developer") //printa "How are you, Scala developer?"

//entry point di un programma è il metodo main, che prende un array di String
object Main {
  def main(args:Array[String]):Unit = 
    println("Hello, Scala developer!")
}







