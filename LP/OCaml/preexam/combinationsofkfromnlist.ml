(* running example 
# extract 2 ["a"; "b"; "c"; "d"];;
- : string list list =
[["a"; "b"]; ["a"; "c"]; ["a"; "d"]; ["b"; "c"]; ["b"; "d"]; ["c"; "d"]] *)

let sublist list startindex endindex = 
  let rec aux acc startindex endindex count = function
  | [] -> acc 
  | h::t -> if (count < startindex) then aux acc startindex endindex (count+1) t else if (startindex >= endindex) then acc else aux (h::acc) (startindex+1) endindex (count+1) t 
  in List.rev (aux [] startindex endindex 0 list) 
;; 

let length list = 
  let rec aux res = function
  | [] -> res 
  | _::t -> aux (res+1) t 
  in aux 0 list
;;


let a = ["a"; "b"; "c"; "d"];;
let b = sublist a 1 3;;


let rec extract k list = 
  if (k <= 0) then [[]] 
  else match list with 
    | [] -> [] 
    | h::t1 -> 
      let with_h = List.map (fun l -> h::l) (extract (k-1) t1) in
      let without_h = extract k t1 in 
    with_h@without_h;;


let b = extract 1 a;;
let c = extract 2 a;;
let d = extract 3 a;;

let e = ["a"; "b"; "c"; "d"; "e"; "f"; "g"; "h"];;

let f = extract 2 e;;

