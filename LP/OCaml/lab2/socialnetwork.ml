type node = {name:string; friendship:string list};;

type graph = 
| Empty
| Network of node list

let create_node str =
  {name = str; friendship = []}
;;

let add_node grph nd = match grph with 
| Empty -> Network [nd] 
| Network nd2 -> Network (nd2@[nd]) 
;;  

let rec change_nodes nt p1 p2 = match nt with 
| Empty -> failwith "empty network"
| Network nt -> 
  let rec aux lst p1 p2 = match lst with 
  | [] -> []
  | h::t -> match (h = p1, h = p2) with 
    | (true, false) -> {name = p1.name; friendship = p1.friendship@[p2.name]}::(aux t p1 p2)
    | (false, true) -> {name = p2.name; friendship = p2.friendship@[p1.name]}::(aux t p1 p2) (*(p2.friendship@[p1.name])@(aux t p1 p2)*)
    | (false, false) -> h::(aux t p1 p2) 
    | (_, _) -> failwith "Same person"
  in aux nt p1 p2 
;; 

let add_friendship str1 str2 grph = match grph with
| Empty -> failwith "Empty network"
| Network nt -> 
  let p1 = List.find (fun elem -> elem.name = str1) nt in 
  let p2 = List.find (fun elem -> elem.name = str2) nt in 
  Network (change_nodes grph p1 p2)
;;

let a = create_node "Simox";;
let b = create_node "Tea";;
let c = create_node "Sam";;
let d = create_node "Fede";;
let g = Empty;;
let g = add_node g a;;
let g = add_node g b;;
let g = add_node g c;;
let g = add_node g d;;

let g = add_friendship "Simox" "Tea" g;;
let g = add_friendship "Simox" "Sam" g;;
let g = add_friendship "Simox" "Fede" g;;

let g = add_friendship "Fede" "Tea" g;;




