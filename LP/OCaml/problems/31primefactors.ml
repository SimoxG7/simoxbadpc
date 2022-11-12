(* running example
# factors 315;;
- : int list = [3; 3; 5; 7] *)

let factors num = 
  let rec aux num curr acc = 
    if (num mod curr = 0) then aux (num/curr) 2 (curr::acc) else if (num = 1) then match acc with 
      | [] -> 1::acc
      | _ -> acc
    else aux num (curr+1) acc
  in List.sort compare (aux num 2 []) 
;;  

factors 315;;

