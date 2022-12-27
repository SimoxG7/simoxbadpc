class Comment {
//Any -> supertipo, fornisce equals, hashCode, toString. Sottoclassi: AnyVal, AnyRef.

//AnyVal -> rappresenta tipi di valori che non possono essere null: Double, Float, Long, Int, Short, Byte, Char, Unit, Boolean. Unit è singleton che non trasporta informazioni. 

//AnyRef -> tipi di riferimenti, ovvero tutto ciò che non è un AnyVal. Ogni tipo definito dall'utente in Scala è un sottotipo di AnyRef. Corrisponde a Object di Java. 

//esempio che Any è padre di tutti gli oggetti
val list: List[Any] = List(
  "a string",
  732,  // an integer
  'c',  // a character
  true, // a boolean value
  () => "an anonymous function returning a string"
)

list.foreach(element => println(element))

//printa: 
// a string
// 732
// c
// true
// <function>

//Casting è unidirezionale: 
  // Byte -> Short -> Int -> Long -> Float -> Double 
  //          Char -> ^ 

val x: Long = 987654321
val y: Float = x.toFloat  // 9.8765434E8 (note that some precision is lost in this case)

val face: Char = '☺'
val number: Int = face  // 9786

//questo non compila, va contro l'unidirezionalità 
val x: Long = 987654321
val y: Float = x.toFloat  // 9.8765434E8
val z: Long = y  // Does not conform

//Nothing è sottotipo di tutti i tipi, detto anche bottom type. Non esiste valore con tipo Nothing. Un tipo di uso comune è per la non-terminazione (eccezione sollevata, uscita del programma, loop infinito).

//Null è sottotipo di tutti i tipi di riferimento (AnyRef). Ha un singolo valore identificato dalla keyword null. Viene usato per interoperabilità con JVM, ma non andrebbe mai usato in codice Scala. 


}