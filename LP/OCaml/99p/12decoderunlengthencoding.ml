(*
#  decode [Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d"; Many (4, "e")];;
- : string list =
["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"]   
*)


type 'a rle = 
| One of 'a
| Many of int * 'a

let decode list = 
  let rec add_n n elem lst = match n with 
    | 1 -> elem::lst
    | n -> add_n (n-1) elem (elem::lst)
  in 
  let rec aux acc = function 
    | [] -> acc 
    | h::t -> match h with 
      | One x -> aux (x::acc) t 
      | Many (n, x) -> aux (add_n n x acc) t 
  in List.rev(aux [] list)
;; 
