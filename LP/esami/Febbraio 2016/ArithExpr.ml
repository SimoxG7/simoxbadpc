module AritExpr = struct 

type expr = 
| Sum of expr * expr 
| Sub of expr * expr 
| Mul of expr * expr 
| Div of expr * expr 
| Number of float
;;

let rec tostring = function
| Number n -> string_of_float n 
| Sum (a, b) -> String.concat " " ["("; (tostring a); " + "; (tostring b); ")"]
| Sub (a, b) -> String.concat " " ["("; (tostring a); " - "; (tostring b); ")"]
| Mul (a, b) -> String.concat " " ["("; (tostring a); " * "; (tostring b); ")"]
| Div (a, b) -> String.concat " " ["("; (tostring a); " / "; (tostring b); ")"]
;;

let rec combine = function
| Number n -> Number n 
| Sum (Number a, Number b) -> Number (a+.b)
| Sum (a, b) -> Sum ((combine a), (combine b))
| Sub (Number a, Number b) -> Number (a-.b)
| Sub (a, b) -> Sub ((combine a), (combine b))
| Mul (Number a, Number b) -> Number (a*.b)
| Mul (a, b) -> Mul ((combine a), (combine b))
| Div (Number a, Number b) -> Number (a/.b)
| Div (a, b) -> Div ((combine a), (combine b))
;;

let rec print_reduction e = 
  print_endline (tostring e);
  match e with 
  | Number n -> ()
  | e -> print_reduction (combine e)
;;

let parse str = 
  let rec parse str n = match str.[n] with 
  | '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' -> 
    Number (float_of_string ((String.make 1 str.[n]) ^ ".")), n
  | '+' ->
    let op1,n1 = (parse str (n+1)) in let op2,n2 = (parse str (n1+1)) in Sum(op1,op2), n2
  | '-' ->
    let op1,n1 = (parse str (n+1)) in let op2,n2 = (parse str (n1+1)) in Sub(op1,op2), n2
  | '*' ->
    let op1,n1 = (parse str (n+1)) in let op2,n2 = (parse str (n1+1)) in Mul(op1,op2), n2
  | '/' ->
    let op1,n1 = (parse str (n+1)) in let op2,n2 = (parse str (n1+1)) in Div(op1,op2), n2
  | _ -> failwith "not parse-able stuff"
in fst (parse str 0);;

let print_evaluation str = print_reduction (parse str);;

end;;


