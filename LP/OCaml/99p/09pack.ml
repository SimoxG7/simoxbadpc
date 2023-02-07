let pack list = 
  let rec aux current acc = function 
    | [] -> []
    | [x] -> (x::current)::acc
    | a::(b::_ as t) -> match a = b with 
      | true -> aux (a::current) acc t 
      | false -> aux [] ((a::current)::acc) t 
  in List.rev(aux [] [] list)
;; 


(* pack ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "d"; "e"; "e"; "e"; "e"];; *)