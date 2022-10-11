let succ = fun x -> x+1;;
(** or even like this *)
let succ x = x+1;;

(** aliasing *)
let succ' = succ;;

succ' 2;;
(fun x -> x+1) 2;;

