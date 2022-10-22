type exn (* tipo delle eccezioni *)
exception ABadThing 
exception OhNo of string 
;;

raise (OhNo "Fuck")
raise ABadThing;;

(* Il tipo exn è built-in, aggiungo costruttori con "exception" . Eccezioni sono varianti estendibili*)

(* exception Failure of string -> eccezioni sollevata da librerie in cui sono indefinit alcuni argomenti *)

(* failwith solleva Failure *)
(* invalid_arg solleva InvalidArgument *)
