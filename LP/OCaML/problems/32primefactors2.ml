(* running example
# factors 315;;
- : (int * int) list = [(3, 2); (5, 1); (7, 1)] *)

let factors num = 
  let make_couple a b = (a, b) in 
  if (num = 1) then [(1, 1)] else 
  let rec aux num curr acc occur = 
    if (num = 1) then (make_couple curr occur)::acc else
      if (num mod curr = 0) then aux (num/curr) curr acc (occur+1) else 
        if (occur != 0) then aux num (curr+1) ((make_couple curr occur)::acc) 0 
        else aux num (curr+1) acc 0
  in List.rev (aux num 2 [] 0) 
;; 

factors 315;;

