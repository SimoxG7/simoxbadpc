let compress list = 
  let rec aux list acc = match list with 
    | [] -> List.rev acc  
    | [a::b::t] -> match a = b with 
      | false -> aux t (a::acc)
      | true -> aux t acc
    | [h::t] -> aux t (h::acc)
  in aux list []
;;