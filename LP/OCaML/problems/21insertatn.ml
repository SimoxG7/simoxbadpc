(* runnning example
# insert_at "alfa" 1 ["a"; "b"; "c"; "d"];;
- : string list = ["a"; "alfa"; "b"; "c"; "d"] *)

let insert_at v pos list = 
  let rec aux v pos = function 
    | [] -> [v]
    | h::t as l -> if (pos=0) then v::l else h::(aux v (pos-1) t)
  in aux v pos list
;;  

let a = ["a"; "b"; "c"; "d"];;
let b = insert_at "alfa" 1 a;;