(* running example
# goldbach 28;;
- : int * int = (5, 23) *)

let is_prime n = 
  let rec aux n div = if (n mod div = 0 && n != 2) then false else if (div > (n/2)) then true else aux n (div+1)
in aux n 2;;

let rec next_prime n = if (is_prime (n+1)) then n+1 else next_prime (n+1);; 
  

let goldbach n = 
  if (n mod 2 = 1) then failwith "The number is odd." else 
  let rec aux n curr = 
    if (is_prime (n-curr) && is_prime curr) then (curr, (n-curr)) else aux n (next_prime curr)
  in aux n 1;;
;;

goldbach 28;;
(* goldbach 29;; *)
goldbach 238198;;
