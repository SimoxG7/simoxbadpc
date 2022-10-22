type 'a mylist = 
  | Nil
  | Cons of 'a * 'a mylist

let rec length = function 
  | Nil -> 0
  | Cons (_, t) -> 1 + (length t)

  



(* type intlist =
  | Nil 
  | Cons of int * intlist (*recursive: h * rest_of_list *)

let rec length = function
  | Nil -> 0
  | Cons (_, t) -> 1 + (length t);

(* per creare intlist *)
Cons (1, Cons(2, Nil));;
*)
