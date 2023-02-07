(*
# slice ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 2 6;;
- : string list = ["c"; "d"; "e"; "f"; "g"]   
*)

let slice list s e = 
  let rec take n = function
  | [] -> []
  | h::t -> match n with 
    | 0 -> []
    | o -> h::(take (o-1) t) 
  in 
  let rec drop n = function
  | [] -> []
  | h::t -> match n with 
    | 1 -> t 
    | o -> drop (o-1) t 
  in take (e-s+1) (drop s list)  
;;



(* let slice list s e = 
  let rec aux s e cont acc = function
  | [] -> acc 
  | h::t -> match s >= cont with 
    | false -> aux s e (cont+1) acc t
    | true -> match e = cont with 
      | true -> acc 
      | false -> aux s e (cont+1) (h::acc) t 
  in List.rev(aux s e 0 [] list) 
;;  *)




