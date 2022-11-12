(* example 
# slice ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"] 2 6;;
- : string list = ["c"; "d"; "e"; "f"; "g"] *)

let slice list startindex endindex = 
  let rec aux acc startindex endindex curr = function
  | [] -> List.rev(acc)  
  | h::t -> if ((curr >= startindex) && (curr <=endindex)) then aux (h::acc) startindex endindex (curr+1) t else aux acc startindex endindex (curr+1) t 
  in aux [] startindex endindex 0 list 
;;

let a = ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"; "i"; "j"];;
let b = slice a 2 6;;