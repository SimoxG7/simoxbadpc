type 'a stream = Nil | Cons of 'a * (unit -> 'a stream) ;;

let hd = function
  | Nil -> failwith "Empty list"
  | Cons (x, _) -> x
;;

let tl = function
  | Nil -> Nil 
  | Cons(_, tl) -> tl()
;;

let rec take n = function 
  | Nil -> []
  | _ when n = 0 -> []
  | Cons (x, tl) -> x::(take (n-1) (tl()))
;;

(* generatore di numeri pari *)
let evens n m = 
  let rec generator i = 
    if (i <= m) then Cons(i, fun() -> generator (i+2))
    else Nil 
  in Cons(n, fun() -> generator(n+2))
;;

take 10 (evens 16 300);;
take 10 (evens 17 100);;





