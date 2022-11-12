(* running example 
# range 4 9;;
- : int list = [4; 5; 6; 7; 8; 9] *)

let rec range st en = 
  let rec aux st en acc = if (st=en) then List.rev(st::acc) else aux (st+1) en (st::acc) in aux st en []
;;

let a = range 4 9;;
