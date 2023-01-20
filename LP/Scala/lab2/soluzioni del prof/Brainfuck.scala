//scalac -cp .:$SCALACLASSPATH Brainfuck.scala
//scala -cp .:$SCALACLASSPATH BrainfuckEvaluator helloworld.bf

import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.matching.Regex 
import scala.collections.mutable

//tutto ciò che non è dei caratteri è spurio e non viene considerato. 

//environment è struttura dati che mantiene lo stato della memoria e ci permette di manipolarla 
class Environment {
  //hashmap di indirizzi di memoria e contenuto di memoria, inzializzata a 0
  private val data = new mutable.HashMap[Int, Int].withDefault(_ => 0)
  private var pointer = 0

  def incrementPointer():Unit = pointer += 1
  def decrementPointer():Unit = pointer -= 1
  def increment():Unit = data(pointer) += 1
  def decrement():Unit = data(pointer) -= 1
  def get():Int = data(pointer)
  def get(n:Int):Int = data(n)
  def put(n:Int):Unit = data(pointer) = n

  override def toString:String = f"^$pointer, " + data.toString()
  
}

object BrainfuckInterpreter {
  def exec(program:Program, env:Environment):Unit = {
    def _exec(expressions:List[Command], env:Environment):Unit = {
      expressions.foreach {
        case IncrementPointer() => env.incrementPointer()
        case DecrementPointer() => env.decrementPointer()
        case IncrementData() => env.increment()
        case DecrementData() => env.decrement()
        //mappiamo il loop sul loop di scala
        case Loop(innerExpression) => while (env.get() > 0) _exec(innerExpression, env)
        case Print() => println(env.get().toChar)
        case Input() => env.put(Console.in.read()) 
        case PrintState() => print(env)
        case _ => throw new IllegalArgumentException
        
      }
    }
    _exec(program.expressions, env)
  }
}


//vogliamo trait generalizzato che mi rappresenta tutti i comandi.
//è un segnaposto
sealed trait Command 
case class IncrementPointer() extends Command 
case class DecrementPointer() extends Command 
case class IncrementData() extends Command 
case class DecrementData() extends Command 
case class Print() extends Command 
case class Input() extends Command 
case class Loop(expressions:List[Command]) extends Command 
case class Program(expressions:List[Command]) extends Command 
case class PrintState() extends Command //extra, per printare cosa sta facendo il programma

object BrainfuckParser extends JavaTokenParsers {
  //JavaTokenParsers butta via in automatico i whitespace. Facciamo override in maniera tale che escluda anche tutto ciò che non sia uno dei nostri caratteri 
  override protected val whiteSpace:Regex = """[^<>\+\-\[\]\.#,]*""".r 

  //~> perchè non ci serve il risultato di quel parsing 
  def loop:Parser[Loop] = "[" ~> rep(command) <~ "]" ^^ {Loop}

  def command:Parser[Command] = ("<" | ">" | "+" | "-" | "." | "," | "#" | loop) ^^ {
    case ">" => IncrementPointer() 
    case "<" => DecrementPointer() 
    case "+" => IncrementData() 
    case "-" => DecrementData() 
    case "." => Print() 
    case "," => Input() 
    case "#" => PrintState() 
    case Loop(expressions) => Loop(expressions) 

  }

  //valore di ritorno sarà un program
  def program:Parser[Program] = rep(command) ^^ { Program }
  
}


object BrainfuckEvaluator {
  def main(args:Array[String]):Unit = {
    args.foreach { filename => 
      val src = scala.io.Source.fromFile(filename)
      val lines = src.mkString 

      BrainfuckParser.parseAll(BrainfuckParser.program, lines) match {
        //primo argomento è risultato del parser, secondo è avanzo dell'input parsato
        case BrainfuckParser.Success(t, _) => BrainfuckInterpreter.exec(t, new Environment)
        case x => print(x.toString) //caso che non dovrebbe succedere
      }
      src.close()
    }
  }
}

