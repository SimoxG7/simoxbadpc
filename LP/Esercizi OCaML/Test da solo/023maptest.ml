let rec map f l =
  match l with
    | [] -> []
    | h::t -> (f h)::(map f t)
;;

let concat_smile x = x ^ ":)";;

let add_one x = x+1;;

let a = [1;2;3];;
let b = ["Hello"; "Ale"; "Addio"];;

map add_one a;;
map concat_smile b;;



