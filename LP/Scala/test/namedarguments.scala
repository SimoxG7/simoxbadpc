def printName(first: String, last: String): Unit =
  println(first + " " + last)

//alla chiamata del metodo posso esplicitare a quale parametro mi riferisco e dunque cambiarne l'ordine di scrittura
printName("John", "Smith")  // Prints "John Smith"
printName(first = "John", last = "Smith")  // Prints "John Smith"
printName(last = "Smith", first = "John")  // Prints "John Smith"

//se però alcuni argomenti hanno il nome specificato e altri no, quelli non specificati devono venire prima di quelli specificati nella signature del metodo
printName(last = "Smith", "john") // error: positional after named argument


