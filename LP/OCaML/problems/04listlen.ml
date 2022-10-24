let len l = 
  let rec aux n = function 
    | [] -> n 
    | _::t -> aux (n+1) t 
  in aux 0 l 
;;


(* let rec len acc = function 
  | [] -> acc 
  | h::t -> len (acc+1) t  *)
(* ;;

 *)
