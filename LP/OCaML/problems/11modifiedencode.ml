type 'a rle = 
  | One of 'a
  | Many of int * 'a 
;;

let encode list =
  let create_tuple count elem = 
    if count = 1 then One elem 
    else Many (count, elem) in  
    let rec aux count acc = function 
      | [] -> []
      | [x] -> (create_tuple (count+1) x)::acc 
      | a::(b::_ as t) -> if (a=b) then aux (count+1) acc t else aux 0 ((create_tuple (count+1) a)::acc) t in 
    List.rev (aux 0 [] list)
  ;; 









(* let encode list = 
  let rec aux count acc  = function 
    | [] -> []
    | [x] -> (count+1, x)::acc
    | a::(b::_ as t) -> if (a=b) then aux (count+1) acc t else aux 0 ((count+1, a)::acc) t 
  in List.rev (aux 0 [] list)
;;  *)

let a = ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"];;
let b = encode a;;









