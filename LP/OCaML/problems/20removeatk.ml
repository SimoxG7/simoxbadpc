(* running example
# remove_at 1 ["a"; "b"; "c"; "d"];;
- : string list = ["a"; "c"; "d"] *)

(* let remove_at n list = 
  let rec aux n acc = function
  | [] -> acc 
  | h::t -> if (n = 0) then List.rev(acc)@t else aux (n-1) (h::acc) t 
  in aux n [] list 
;; *)

let rec remove_at n = function
  | [] -> []
  | h::t -> if (n=0) then t else h::(remove_at (n-1) t) 
;;


let a = ["a"; "b"; "c"; "d"];;
let b = remove_at 2 a;;