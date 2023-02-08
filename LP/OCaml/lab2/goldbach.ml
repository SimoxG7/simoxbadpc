let prime n = 
  let rec aux n div = if div >= n then true else match n mod div with 
  | 0 -> false 
  | _ -> aux n (div+1) 
  in aux n 2
;;

let prime_list s e = List.filter (fun y -> prime y) (List.init (e-s+1) (fun x -> s+x));;

let goldbach n = if n mod 2 = 1 then failwith "not an even number" else 
  let rec aux n = function
  | [] -> failwith "no existing goldbach partition for the number"
  | h::t -> if prime (n - h) then (n, (h, (n-h))) else aux n t 
  in aux n (prime_list 2 n)
;;

let rec goldbach_list s e = if s mod 2 = 1 then goldbach_list (s+1) e else 
  if s > e then [] else (goldbach s)::(goldbach_list (s+2) e)
;;



