type 'a stream = Nil | Cons of 'a * (unit -> 'a stream) ;;

let hd = function
  | Nil -> failwith "Empty list"
  | Cons (x, _) -> x
;;

let tl = function
  | Nil -> Nil 
  | Cons(_, tl) -> tl()
;;

(* generatore di numeri pari *)
let evens n m = 
  let rec generator i = 
    if (i <= m) then Cons(i, fun() -> generator (i+2))
    else Nil 
  in Cons(n, fun() -> generator(n+2))
;;





