type 'a rle =
| One of 'a
| Many of int * 'a;;


let decode list =
  let rec insert_n_times n v acc = if (n <> 0) then insert_n_times (n-1) v (v::acc) else acc in 
    let rec aux acc = function 
    | [] -> acc
    | One x::t -> aux (x::acc) t 
    | Many (n, v)::t -> aux (insert_n_times n v acc) t
  in List.rev (aux [] list)
;;

decode [Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d"; Many (4, "e")];;