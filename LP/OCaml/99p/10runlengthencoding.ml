(*
encode ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"];;
- : (int * string) list =
[(4, "a"); (1, "b"); (2, "c"); (2, "a"); (1, "d"); (4, "e")]
*)

let encode list = 
  let rec aux cont acc = function 
    | [] -> []
    | [x] -> (cont, x)::acc
    | a::(b::_ as t) -> match a = b with 
      | true -> aux (cont+1) acc t 
      | false -> aux 1 ((cont, a)::acc) t 
  in List.rev(aux 1 [] list)
;; 


 