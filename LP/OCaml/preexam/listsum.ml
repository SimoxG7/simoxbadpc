
let fold list = 
  let rec aux res = function
  | [] -> res 
  | h::t -> aux (res + h) t 
  in aux 0 list
;;

let remove_zeroes list = 
  let rec aux acc = function
  | [] -> acc 
  | h::t -> if (h = 0) then aux acc t else aux (h::acc) t 
  in List.rev (aux [] list)
;;

let add_till_zero list = 
  let rec aux accinner accouter = function
  | [] -> accouter
  | h::t -> if (h = 0) then aux [] ((fold accinner)::accouter) t else aux (h::accinner) accouter t 
  in remove_zeroes (List.rev (aux [] [] list))
;;

let a = add_till_zero [1;2;3;0;6;7;0;0;9;9;0;0;0];;




