(* let duplicate list = 
  let rec aux acc = function 
    | [] -> acc 
    | h::t -> aux (h::h::acc) t 
  in List.rev (aux [] list)
;;  *)

(* with @ *)
let duplicate list = 
  let rec aux acc = function 
    | [] -> acc 
    | h::t -> aux (acc@[h]@[h]) t 
  in aux [] list
;; 



(* in *)
let a = ["a"; "b"; "c"; "c"; "d"];;
let b = duplicate a;;

(* out
- : string list = ["a"; "a"; "b"; "b"; "c"; "c"; "c"; "c"; "d"; "d"] *)