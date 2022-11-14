let contiguous_region lst = 
  let rec aux acc count = function
  | [] -> acc 
  | [x] -> (count::acc) 
  | h::(p::_ as t) -> if h = p then aux acc (count+1) t else aux (count::acc) 1 t
  in List.rev (aux [] 1 lst)
;;
