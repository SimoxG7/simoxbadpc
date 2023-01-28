let rec nth pos = function 
  | [] -> None  
  | h::t -> match pos with 
    | 0 -> Some h 
    | _ -> nth (pos-1) t 
;;