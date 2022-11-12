(* drop every nth *)
let dropnth list n = 
  let rec aux acc n curr = function
  | [] -> acc 
  | h::t -> if (n = curr) then aux acc n 1 t else aux (h::acc) n (curr+1) t 
  in List.rev (aux [] n 1 list)
;; 

let a = ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"];;
let b = dropnth a 3;;

(* out 
- : string list = ["a"; "b"; "d"; "e"; "g"; "h"; "j"] *)