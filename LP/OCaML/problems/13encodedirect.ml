type 'a rle = 
  | One of 'a 
  | Many of int * 'a 
;;

let encode list = 
  let create_tuple n v = if (n>1) then Many (n, v) else One v in 
  let rec aux acc n = function 
    | [] -> acc 
    | [x] -> (create_tuple (n+1) x)::acc
    | a::(b::_ as t) -> if (a=b) then aux (acc) (n+1) t else aux ((create_tuple (n+1) a)::acc) 0 t;
  in List.rev (aux [] 0 list)
;;


let a = ["a";"a";"a";"a";"b";"c";"c";"a";"a";"d";"e";"e";"e";"e"];;
let b = encode a;;

(* out 
[Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d"; Many (4, "e")]
*)


(* type 'a rle =
  | One of 'a 
  | Many of int * 'a
;;

let rec insert_n_times n v out = if (n = 0) then out else insert_n_times (n-1) v (v::out)

let decode list = 
  let rec aux out = function 
    | [] -> out
    | One x::t -> aux (x::out) t
    | Many (n,v)::t -> aux (insert_n_times n v out) t
  in List.rev (aux [] list)
;;

let a = [Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d"; Many (4, "e")];; *)



(* encode ["a";"a";"a";"a";"b";"c";"c";"a";"a";"d";"e";"e";"e";"e"];; *)


(* output
- : string rle list =
[Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d";
 Many (4, "e")] *)