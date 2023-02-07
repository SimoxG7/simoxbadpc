(*
# insert_at "alfa" 1 ["a"; "b"; "c"; "d"];;
- : string list = ["a"; "alfa"; "b"; "c"; "d"]
*)

let rec insert_at elem pos = function
| [] -> [elem] 
| h::t as l -> match pos with 
  | 0 -> elem::l
  | n -> h::(insert_at elem (pos-1) t)
;;

