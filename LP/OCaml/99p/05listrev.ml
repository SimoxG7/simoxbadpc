let rev lst = 
  let rec aux lst acc = match lst with
    | [] -> acc
    | h::t -> aux t (h::acc)
  in aux lst [] 
;;