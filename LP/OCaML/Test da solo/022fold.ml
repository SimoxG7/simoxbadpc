(* combine/fold *)

(* sum of elements *)
let rec sum = function
  | [] -> 0
  | h::t -> 1 + sum t
;;

let rec concat = function
  | [] -> ""
  | h::t -> h ^ concat t
;;

(* sono due funzioni molto simili, differiscono da nome, operazione e risultato della prima linea *)
(* astraiamo *)

let rec combine init op = function 
  | [] -> init (* initial value*)
  | h::t -> op h (combine init op t) (* operazione *)

let sum' l = combine 0 ( + ) l
let concat' l = combine "" ( ^ ) l 

(* il codice di combine è simile a quello di fold, un concetto simile al reduce (accumuliamo un risultato applicando una funzione a elementi della lista)*)

(* fold può essere da destra o da sinistra *)

(* let rec fold_right f acc = function
  | [] -> acc (* ritorniamo l'accumulatore, ovvero ciò che viene accumulato (inizio 0 )*)
  | h::t -> f h (fold_right f t acc) *)

(* nella libreria di ocaml la lista viene passata come argomento centrale, quindi non si può fare il pattern matching nascondendo la variabile *)
let rec fold_right f l acc = 
  match l with
    | [] -> acc 
    | h::t -> f h (fold_right f t acc) (* foldiamo da destra perchè prima folda tutte le tail e alla fine le head (stack )*)
;;

let rec fold_left f acc l = match l with 
  | [] -> acc 
  | h::t -> fold_left f (f acc h) t (* ottengo nuovo accumulatore *)

(* il fold_left e fold_right portano risultati diversi se l'operazione non è commutativa e associativa (ad esempio non ho problemi con +, ma ho problemi con -)*)

(* in fold left non abbiamo più lavoro da fare dopo la chiamata ricorsiva -> ricorsione in coda e dunque più efficiente. fold right non è invece tail recursive -> stack cresce molto di più *)

(* se voglio avere tail_recursive su fold_right devo fare reverse della lista e poi fold_left *)











