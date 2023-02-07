(*
# duplicate ["a"; "b"; "c"; "c"; "d"];;
- : string list = ["a"; "a"; "b"; "b"; "c"; "c"; "c"; "c"; "d"; "d"]   
*)

let duplicate list = 
  let rec aux acc = function
  | [] -> acc 
  | h::t -> aux (h::h::acc) t 
  in List.rev(aux [] list)
;;