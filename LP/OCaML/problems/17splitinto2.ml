(* running example 
# split ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 3;;
- : string list * string list =
(["a"; "b"; "c"], ["d"; "e"; "f"; "g"; "h"; "i"; "j"])
# split ["a"; "b"; "c"; "d"] 5;;
- : string list * string list = (["a"; "b"; "c"; "d"], []) *)


let split list n = 
  let rec aux acc n curr = function 
    | [] -> (List.rev(acc), [])
    | h::t -> if (n = curr) then (List.rev(acc), h::t) else aux (h::acc) n (curr+1) t
  in  aux [] n 0 list 
;;

let a = ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"];;
let b = split a 3;;

let c = ["a"; "b"; "c"; "d"];;
let d = split c 5;;