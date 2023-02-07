(*
# rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3;;
- : string list = ["d"; "e"; "f"; "g"; "h"; "a"; "b"; "c"]   
*)

let rotate list n = 
  let rec aux n acc = function
  | [] -> List.rev(acc)
  | h::t as l -> match n with 
    | 0 -> l@List.rev(acc)
    | other -> aux (other-1) (h::acc) t 
  in (aux n [] list)
;; 
