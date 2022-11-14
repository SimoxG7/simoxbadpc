let remove_zero lst = match lst with 
| [] -> []
| _::t -> t 
;;

let contiguous_region lst = 
  let rec aux acc prev res lst = match lst with 
  | [] -> res::acc 
  | h::t -> if (h = prev) then aux acc h (res+1) t else aux (res::acc) h 1 t 
  in remove_zero (List.rev (aux [] (-1) 0 lst))
;;

let a = contiguous_region [1;2;3;4];;
let b = contiguous_region [1;1;1;1;2;3;3;3;4;4;4;1;1;1;1;1;1;2];;
let c = contiguous_region [];;
let d = contiguous_region [1;2;2;3;3;3;4;4;4;4];;



 