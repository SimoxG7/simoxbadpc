type 'a node =
  | One of 'a 
  | Many of 'a node list 
;;

let a = [One "a"; Many [One "b"; Many [One "c"; One "d"]; One "e"]];;

let flatten list = 
  let rec aux acc = function 
    | [] -> acc 
    | One x::t -> aux (x::acc) t 
    | Many l::t -> aux (aux acc l) t
  in List.rev (aux [] list)
;;

(* let flatten node = 
  let rec aux list n = 
    match n with 
    | One e -> List.rev_append list e 
    | Many newnode -> match newnode with | [] -> list | h::t -> List.rev_append list (aux list h)
  in aux [] node
;; *)
  
flatten a;; 