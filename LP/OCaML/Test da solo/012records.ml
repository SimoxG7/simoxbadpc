(* i record sono eterogenei, sono tuple mutabili e i loro campi sono accessibili *)
type person = {name: string; mutable age: int};;
let p = {name = "Osama"; age = 52};;
p.name;; (* stampa Osama *)
p.age <- p.age +1;;
p;;
(* provare a cambiare il nome invece dà errore *)

