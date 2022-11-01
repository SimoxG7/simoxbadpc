(* Running example
# lotto_select 6 49;;
- : int list = [20; 28; 45; 16; 24; 38] *)

open Random;;
open List;;

let lotto_select n max = 
  let rec aux n max acc = if (n=0) then acc else aux (n-1) max ((List.nth (List.init max) (Random.int max))::acc) 
in aux n max []
;;


let a = [20; 28; 45; 16; 24; 38];;
let b = lotto_select 6 a;;
