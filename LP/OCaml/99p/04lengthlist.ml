let length lst = 
  let rec aux lst cont = match lst with 
    | [] -> cont 
    | _::t -> aux t (cont+1)
  in aux lst 0
;;