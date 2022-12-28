class Comment 

//le trait si indicano con la keyword trait e un identificatore:
trait HairColor 

//le trait sono particolarmente utili con i tipi generici e con metodi astratti
trait Iterator[A] {
  def hasNext:Boolean
  def next:A
}

//per estendere il trait Iterator[A] occorre un tipo A e l'implementazione dei metodi hasNext e Next. 

//per estendere un trait è necessario usare la keyword extends e implementare i metodi astratti del trait. 

trait Iterator[A] {
  def hasNext:Boolean 
  def next:A
}

//prende to come upperbound, estende Iterator[Int] e quindi next deve ritornare un Int 
class IntIterator(to:Int) extends Iterator[Int] {
  
  private var current = 0
  
  override def hasNext:Boolean = current < to 

  override def next:Int = {
    if (hasNext) {
      current += 1
      current
    } else 0
  }
}

val iterator = new IntIterator(10)
iterator.next()
iterator.next() 

//dove è richiesta una trait, ne posso usare un sottotipo 
import scala.collection.mutable.ArrayBuffer

//la trait Pet ha un campo astratto nome che viene implmentato da Cat e Dog nei loro costruttori 
trait Pet { val name:String }

class Cat(val name:String) extends Pet 
class Dog(val name:String) extends Pet 

val dog = new Dog("Harry")
val cat = new Cat("Sally")

val animals = ArrayBuffer.empty[Pet]
animals.append(dog)
animals.append(cat)
//pet.name è implementato in ogni sottotipo di Pet 
animals.foreach(pet => println(pet.name)) //printa Harry Sally












