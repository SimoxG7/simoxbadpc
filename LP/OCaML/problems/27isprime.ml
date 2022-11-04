(* running examples:
# not (is_prime 1);;
- : bool = true
# is_prime 7;;
- : bool = true
# not (is_prime 12);;
- : bool = true *)

let is_prime num = 
  let rec aux num div = if ((num mod div) = 0 && (num != 2)) then false else if (div > (num/2)) then true else   aux num (div+1)
in aux num 2;;

is_prime 1;;
is_prime 7;;
is_prime 12;;

let create_int_list_to_n n =
  let rec aux max curr acc = if (curr = max) then acc else aux max (curr+1) (curr::acc) 
in List.rev (aux n 1 [])
;;

let a = create_int_list_to_n 100;;

let rec map f = function 
  | [] -> []
  | h::t -> f h :: map f t
;;

let b = map is_prime a;;

let rec unify a b acc = match a with 
  | [] -> acc
  | h::t -> acc 


