let rev list =
  let rec aux acc = function 
    | [] -> acc 
    | h::t -> aux (h::acc) t 
  in aux [] list
;;

(* let rec rev = function 
| [] -> []
| h::t -> (rev t) @ [h] *)

let a = [1; 2; 3; 4];;