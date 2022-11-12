type primary_color = Red | Green | Blue 

let r = Green

type point = float * float 

(* questi sono costruttori *)
type shape = 
  | Circle of {center : point; radius : float}
  | Rectangle of {lower_left : point; upper_right : point}
  | Point of point 

let c1 = Circle {center = (0., 0.); radius = 1.}
let r1 = Rectangle {lower_left = (-1., -1.); upper_right = (1., 1.)}

let avg a b = (a +. b) /. 2.

(*
let center s = 
  match s with
    | Circle {center; radius} -> center
    | Rectangle {lower_left; upper_right} -> 
      let (x_ll, y_ll) = lower_left in 
      let (x_ur, y_ur) = upper_right in
      (avg x_ll x_ur, avg y_ll y_ur)
*)

(*miglioriamo il pattern matching di varianti*)

let center s = 
  match s with
    | Circle {center; radius} -> center
    | Rectangle {lower_left = (x_ll, y_ll); upper_right = (x_ur, y_ur)} -> (avg x_ll x_ur, avg y_ll y_ur)
    | Point p -> p
    (* | Point (x, y) -> (x,y) *)



let r4 = Rectangle {lower_left = (0.,0.); upper_right = (2.,2.)};;


(* Exception: failwith "message"
   oppure: assert false
*)
