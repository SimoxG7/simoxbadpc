//mixins sono trait usati per comporre classi 
abstract class A {
  val message:String 
}

class B extends A {
  val message = "I'm an instance of class B"
}

trait C extends A {
  def loudMessage = message.toUpperCase()
}

//classe D ha superclasse B e mixin C. Le classi possono avere una sola superclasse, ma tanti mixins. 
class D extends B with C 

val d = new D 
println(d.message)  // I'm an instance of class B
println(d.loudMessage)  // I'M AN INSTANCE OF CLASS B


//altro esempio più interessante
abstract class AbsIterator {
  type T
  def hasNext: Boolean
  def next(): T
}

//classe per iterare su una stringa 
class StringIterator(s:String) extends AbsIterator {
  type T = Char 
  private var i = 0
  def hasNext = i < s.length
  def next = {
    val ch = s charAt i 
    i += 1
    ch
  }
}

//trait che estende AbsIterator:
//trait implementa il foreach chiamando la funzione f:T => Unit sul prossimo elemento finchè ci sono elementi. Poichè è trait, non deve implementare i metodi astratti di AbsIterator
trait RichIterator extends AbsIterator {
  def foreach(f:T => Unit):Unit = while(hasNext) f(next)
}

//per combinare le funzionalità di RichIterator e di StringIterator in una sola classe:
class RichStringIterator extends StringIterator("Scala") with RichIterator
val rsi = new RichStringIterator
rsi.foreach(println)

//ora la classe RichStringIterator ha StringIterator come superclasse e RichIterator come mixin.







