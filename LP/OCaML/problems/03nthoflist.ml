let rec nth n = function 
  | [] -> failwith "nth"
  | h::t -> if n == 0 then Some h else nth (n-1) t
;;

(* let rec nth n i = function 
  | [] -> failwith "nth"
  | h::t -> if i == n then Some h else nth n (i+1) t
;; *)
