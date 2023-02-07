(* # replicate ["a"; "b"; "c"] 3;;
- : string list = ["a"; "a"; "a"; "b"; "b"; "b"; "c"; "c"; "c"] *)

let replicate list n = 
  let rec add_n elem times acc = match times with 
    | 1 -> elem::acc 
    | n -> add_n elem (times-1) (elem::acc) 
  in 
  let rec aux acc n = function
    | [] -> acc 
    | h::t -> aux (add_n h n acc) n t
  in List.rev(aux [] n list)
;;

