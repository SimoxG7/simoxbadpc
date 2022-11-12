let replicate_n_times list n =
  let rec insert_n_times n v acc = if (n = 0) then acc else insert_n_times (n-1) v (v::acc) in 
    let rec aux acc n = function 
    | [] -> acc 
    | h::t -> aux (insert_n_times n h acc) n t
  in List.rev (aux [] n list)
;; 

(* in *)
let a = ["a"; "b"; "c"];;
let b = replicate_n_times a 3;;

(* out *)
(* - : string list = ["a"; "a"; "a"; "b"; "b"; "b"; "c"; "c"; "c"] *)