class Comment 

//Scala permette di usare dei valori di default dei parametri che permettono di ometterli in fase di costruzione. Un po' come python.
def log(message:String, level:String = "INFO") = println(s"$level: $message")
//level ha un valore di default ed è pertanto opzionale

log("System starting") //printa "INFO: System starting"
log("User not found", "WARNING") //printa "WARNING: User not found", fa override del valore di default 

//se viene omesso un argomento, deve essere specificato a quale parametro mi riferisco 
class Point(val x:Double = 0, val y:Double = 0)
val point = new Point(y = 3)

//Scala non permette di avere due metodi con lo stesso nome e parametri di default 
object A {
  def func(x: Int = 34): Unit
  def func(y: String = "abc"): Unit
}

//alla chiamata di A.func(), il compilatore non sa quale dei due usare






