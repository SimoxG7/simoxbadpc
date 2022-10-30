type 'a rle =
  | One of 'a 
  | Many of int * 'a
;;

let rec insert_n_times n v out = if (n = 0) then out else insert_n_times (n-1) v (v::out)

let rec decode list out = match list with
  | [] -> []
  | One x::t -> decode t (x::out)
  | Many (n,v)::t -> decode t (insert_n_times n v out)
;;

decode [Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d"; Many (4, "e")];;




(* encode ["a";"a";"a";"a";"b";"c";"c";"a";"a";"d";"e";"e";"e";"e"];; *)


(* output
- : string rle list =
[Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d";
 Many (4, "e")] *)