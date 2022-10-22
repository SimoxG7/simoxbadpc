(* map trasforma elementi di una lista *)
let rec add_one = function 
  | [] -> []
  | h::t -> (h + 1)::(add_one t)
;;

let rec concat3110 = function 
  | [] -> []
  | h::t -> (h ^ string_of_int 3110)::(concat3110 t)
;;

let rec map f = function 
  | [] -> []
  | h::t -> f h :: map f t
;;

(* in questo modo posso fare add_one così: *)

let add_one l = map (fun x -> x+1) l;;

(* mappa prende una funzione di tipo 'a -> 'b e una lista di tipo 'a list , ritornando una lista 'b list*)

let concat_smile l = map (fun str -> str ^ ":)") l;;



