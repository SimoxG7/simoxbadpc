(* running example
# goldbach_list 9 20;;
- : (int * (int * int)) list =
[(10, (3, 7)); (12, (5, 7)); (14, (3, 11)); (16, (3, 13)); (18, (5, 13));
 (20, (3, 17))] *)


module Goldbach = struct
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
end

open Goldbach;;

let goldbachlist s e = 
  let rec aux s e acc = 
    if (s > e) then acc else if (s mod 2 = 0) then aux (s+2) e ((s, (goldbach s))::acc) else aux (s+1) e acc 
  in List.rev (aux s e [])
;;

goldbachlist 9 20;;