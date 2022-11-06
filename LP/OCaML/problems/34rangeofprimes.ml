(* running example
# List.length (all_primes 2 7920);;
- : int = 1000 *)

module Prime = struct
  let is_prime num = 
    let rec aux num div = if ((num mod div) = 0 && (num != 2)) then false else if (div > (num/2)) then true else   aux num (div+1)
  in aux num 2;;
end

open Prime;;

let range_of_primes sindex eindex = 
  let rec aux s e acc = 
    if (s = e) then acc else if (is_prime s) then aux (s+1) e (s::acc) else aux (s+1) e acc 
  in List.rev (aux sindex eindex [])
;;

let a = range_of_primes 2 7920;;
List.length a;;

