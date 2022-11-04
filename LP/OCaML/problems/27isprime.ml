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

let make_couple a b = (a, b);;

let unify a b =
  let rec aux a b acc = match a with 
  | [] -> acc
  | ha::ta -> match b with 
    | [] -> acc 
    | hb::tb -> aux ta tb ((make_couple ha hb)::acc)
in List.rev (aux a b []);;
;;

let c = unify a b;;

let primes_only list =
  let rec aux acc =  function
    | [] -> acc
    | h::t -> if (is_prime h) then aux (h::acc) t else aux acc t
  in List.rev (aux [] list) 
;;

let d = primes_only a;;












