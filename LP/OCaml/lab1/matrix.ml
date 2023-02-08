(*
Write the matrix datatype with the following operations:

A function zeroes to construct a matrix of size n×m filled with zeros.
A function identity to construct the identity matrix (the one with all 0s but the 1s on the diagonal) of given size.
A function init to construct a square matrix of a given size n filled with the first n×n integers.
A function transpose that transposes a generic matrix independently of its size and content.
The basics operators + and * that adds and multiplies two matrices non necessarily squared.

*)

let zeroes n m = List.init n (fun x -> List.init m (fun y -> 0));;

let identity n = List.init n (fun x -> List.init n (fun y -> match x = y with 
  | true -> 1 
  | false -> 0
));;

let init n = List.init n (fun x -> List.init n (fun y -> n*x + y +1));;

let rec transpose = function
| [] -> []
| []::xss -> transpose xss 
| (x::xs)::xss -> (x::(List.map List.hd xss))::(transpose (xs::(List.map List.tl xss))) 
;;


let vect_prod vect1 vect2 = 
  let rec aux vect1 vect2 res = match vect1, vect2 with 
    | h1::t1, h2::t2 -> aux t1 t2 (res + (h1 * h2))
    | _,_ -> res
  in aux vect1 vect2 0 
;;

let ( + ) m1 m2 =   List.map2 (fun r1 r2 -> List.map2 (fun v1 v2 -> v1 + v2) r1 r2) m1 m2;;

let ( * ) mat1 mat2 = 
  let rec mat_prod mat1 mat2 = 
    let cols = transpose mat2 in 
      List.map (fun row -> List.map (vect_prod row) cols) mat1 
  in mat_prod mat1 mat2
;;





