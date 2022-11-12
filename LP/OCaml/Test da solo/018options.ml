type 'a option = None | Some of 'a

(* come scatola: o è vuota o ha qualcosa dentro e quello che ha dentro è l'alpha *)
(* option sono varianti, possiamo usare pattern matching *)

let get_val default = function
  | None -> default
  | Some x -> x

let rec list_max (lst : 'a list) : 'a option = 
  match lst with
    | [] -> None
    | h::t -> begin 
      match list_max t with 
      | None -> Some h 
      | Some m -> Some (max h m)
    end

(* usare begin e end in nested pattern matches *)

let x = list_max [1;2;3] (* ritorna 3 *)
let y = list_max [] (* -> cosa dovrebbe tornare?*)