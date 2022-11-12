type matrix = int list list;;

let zeroes a b = 
  List.init a (fun x -> List.init b (fun x -> 0))
;;

let identity a = 
  List.init a (fun x -> List.init a (fun y -> if x = y then 1 else 0))
;;

let init a = 
  List.init a (fun x -> List.init a (fun y -> x*a + y +1))
;;

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

let ( + ) mat1 mat2 =
  List.map2 (fun r1 r2 -> List.map2 (fun v1 v2 -> v1 + v2) r1 r2) mat1 mat2;;

let a = init 4;;
let b = init 4;;

let c = a + b;;

let ( * ) mat1 mat2 = 
  let rec mat_prod mat1 mat2 = 
    let cols = transpose mat2 in 
      List.map (fun row -> List.map (vect_prod row) cols) mat1 
  in mat_prod mat1 mat2
;;

let d = c * c;;
