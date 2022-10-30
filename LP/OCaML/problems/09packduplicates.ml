
let packdup list = 
  let rec aux current acc = function 
    | [] -> []
    | [x] -> (x::current)::acc 
    | a::(b::_ as t) -> if (a=b) then aux (a::current) acc t else aux [] ((a::current)::acc) t 
  in List.rev(aux [] [] list)
;; 



(* let pack l = 
  let rec aux current acc = function 
    | [] -> []
    | [x] -> (x::current) :: acc 
    | a::(b::_ as t) -> 
      if (a = b) then aux (a::current) acc t 
      else aux [] ((a::current)::acc) t
    in List.rev (aux [] [] l)
  ;; *)


let a = ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "d"; "e"; "e";];;

