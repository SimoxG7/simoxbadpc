(* running example
# rotate ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"] 3;;
- : string list = ["d"; "e"; "f"; "g"; "h"; "a"; "b"; "c"] *)

(* let rotate_n_to_left list n = 
  let rec aux acc n curr = function
  | [] -> [] (*reached only if given list is empty? *)
  | h::t -> if (curr < n) then aux (h::acc) n (curr+1) t else [h]@t@List.rev(acc)
in aux [] n 0 list
;; *)

let rotate_n_to_left list n = 
  let rec aux acc n curr = function
  | [] -> [] (*reached only if given list is empty? *)
  | h::t -> if (curr < n) then aux (h::acc) n (curr+1) t else (h::t)@List.rev(acc)  
in aux [] n 0 list
;;

let a = ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"];;
let b = rotate_n_to_left a 3;;