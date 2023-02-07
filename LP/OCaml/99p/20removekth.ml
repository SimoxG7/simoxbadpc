(*
# remove_at 1 ["a"; "b"; "c"; "d"];;
- : string list = ["a"; "c"; "d"]   
*)

let rec remove_at n = function
| [] -> []
| h::t -> match n with 
  | 0 -> t 
  | o -> h::(remove_at (o-1) t)
;;


(*
let remove_at n list = 
  let rec aux n acc = function
  | [] -> acc 
  | h::t -> match n with 
    | 0 -> List.rev(acc)@t 
    | o -> aux (n-1) (h::acc) t 
  in aux n [] list 
;;
*)



