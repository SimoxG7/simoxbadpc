(* elimina duplicati consecutivi *)

let rec compress = function 
  | a::(b::_ as t) -> if (a=b) then compress t else a::compress t 
  | a::[] -> a::[]
  | [] -> []
;;


(* 
let rec compress = function
  | a::(b::_ as t) -> if (a = b) then compress t else a::compress t
  | single -> single
  (* | a::[] -> a::[]
  | [] -> [] *)
;; 
  *)


let a = ["a"; "a"; "a"; "a"; "b"; "a"; "a"; "a"; "a"; "b"; "b"];;

compress a;;



