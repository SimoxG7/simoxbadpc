//tuple: valore che contiene un numero fisso di elementi, ciascuno con il suo tipo. Sono immutabili. Sono molto utili per ritornare valori multipli da un metodo

//tupla di tipo (inferito) (String, Int)
val ingredient = ("sugar", 25)

//per accedere al campo 1 -> ._1, al campo x -> ._x
println(ingredient._1)

//per fare pattern matching
//viene inferito il tipo di name e il tipo di quantity
val (name, quantity) = ingredient 
println(name)
println(quantity)

//altro esempio di pattern matching di una tupla:
val planets = List(("Mercury", 57.9), ("Venus", 108.2), ("Earth", 149.6), ("Mars", 227.9), ("Jupiter", 778.3))

planets.foreach {
  case ("Earth", distance) =>
    println(s"Our planet is $distance million kilometers from the sun")
  case _ =>
}

//pattern matching in for comprehension 
val numPairs = List((2, 5), (3, -7), (20, 56))
for ((a, b) <- numPairs) {
  println(a * b)
}









