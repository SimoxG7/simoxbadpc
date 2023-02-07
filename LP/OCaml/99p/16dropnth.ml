(*
# drop ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 3;;
- : string list = ["a"; "b"; "d"; "e"; "g"; "h"; "j"]    
*)

let drop lst n = 
  let rec aux n cont acc = function
  | [] -> acc 
  | h::t -> match cont = n with 
    | true -> aux n 1 acc t
    | false -> aux n (cont+1) (h::acc) t 
  in List.rev(aux n 1 [] lst)
;;
