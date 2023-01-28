type 'a node = 
  | One of 'a
  | Many of 'a node list 
;;

let flatten list = 
  let rec aux list acc = match list with  
    | [] -> List.rev acc 
    | One h :: t -> aux t (h::acc)
    | Many l :: t -> aux t (aux l acc) 
  in aux list []
;;
