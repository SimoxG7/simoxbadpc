(*
# split ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 3;;
- : string list * string list =
(["a"; "b"; "c"], ["d"; "e"; "f"; "g"; "h"; "i"; "j"])
# split ["a"; "b"; "c"; "d"] 5;;
- : string list * string list = (["a"; "b"; "c"; "d"], [])
*)

let split list pos = 
  let rec aux acc pos = function
  | [] -> (List.rev(acc), []) 
  | h::t -> match pos = 1 with 
    | true -> (List.rev(h::acc), t) 
    | false -> aux (h::acc) (pos-1) t 
  in aux [] pos list 
;; 
