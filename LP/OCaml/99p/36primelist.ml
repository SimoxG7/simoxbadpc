let is_prime n = 
  let rec aux n div = match n = div with 
    | true -> true 
    | false -> match n mod div with 
      | 0 -> false 
      | _ -> aux n (div+1) 
  in aux n 2
;; 

let prime_list n = 
  let rec aux = function
  | [] -> []
  | h::t -> match is_prime(h) with 
    | true -> h::(aux t)
    | false -> aux t 
  in (aux (List.init (n-1) (fun x -> x+2)))
;;