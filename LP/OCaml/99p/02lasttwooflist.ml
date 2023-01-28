
let rec last_two = function 
  | [] -> None 
  | [x] -> None 
  | [x1; x2] -> Some (x1,x2) 
  | _::t -> last_two t 
;;
