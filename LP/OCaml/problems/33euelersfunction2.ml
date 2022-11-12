let rec pow n e = if (e < 1) then 1 else n * (pow n (e-1));;

module Factors = struct
  let factors num = 
    let make_couple a b = (a, b) in 
    if (num = 1) then [(1, 1)] else 
    let rec aux num curr acc occur = 
      if (num = 1) then (make_couple curr occur)::acc else
        if (num mod curr = 0) then aux (num/curr) curr acc (occur+1) else 
          if (occur != 0) then aux num (curr+1) ((make_couple curr occur)::acc) 0 
          else aux num (curr+1) acc 0
    in List.rev (aux num 2 [] 0) 
end;;

open Factors;;

let euler n = 
  let rec aux acc = function
  | [] -> acc 
  | (p, m)::t -> aux ((p-1) * (pow p (m-1) ) * acc) t 
in aux 1 (factors n)
;; 

euler 10;;
euler 13;;