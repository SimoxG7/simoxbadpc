let rec last = function 
  | [] -> None
  | [x] -> Some x 
  | _::t -> last t
  ;;

(* let rec last acc = function
  | [] -> acc
  | h::t -> last h t *)
(* ;; *)
