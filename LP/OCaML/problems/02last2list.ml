let rec last2 = function 
  | [] | [_] -> None 
  | [x; y] -> Some (x, y)
  | _::t -> last2 t 
;;

