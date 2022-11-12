(* running example
# rand_select ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3;;
- : string list = ["g"; "d"; "a"] *)

open Random;;

let list_len list = 
  let rec aux v = function
    | [] -> v 
    | h::t -> aux (v+1) t 
  in aux 0 list
;;

let rec extract n = function
  | [] -> failwith "Empty"
  | h::t -> if (n = 0) then h else extract (n-1) t 

let rec rand_select list n acc = if (n=0) then acc else rand_select list (n-1) ((extract ( Random.int (list_len list)) list)::acc);;

let a = ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"];;
let b = rand_select a 3 [];;
  
