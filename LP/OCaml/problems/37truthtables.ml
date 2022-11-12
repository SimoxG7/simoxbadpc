type bool_expr =
  | Var of string
  | Not of bool_expr
  | And of bool_expr * bool_expr
  | Or of bool_expr * bool_expr;;

(* esempio espressione  
# And (Or (Var "a", Var "b"), And (Var "a", Var "b"));;
- : bool_expr = And (Or (Var "a", Var "b"), And (Var "a", Var "b")) *)
  
(* # table2 "a" "b" (And (Var "a", Or (Var "a", Var "b")));;
- : (bool * bool * bool) list =
[(true, true, true); (true, false, true); (false, true, false);
 (false, false, false)] *)



let rec eval2 a val_a b val_b expr = match expr with
  | Var x -> if x = a then val_a else if x = b then val_b else failwith "Unrecognized variable"
  | Not e -> not (eval2 a val_a b val_b expr) 
  | And (e1, e2) -> eval2 a val_a b val_b e1 && eval2 a val_a b val_b e2 
  | Or (e1, e2) -> eval2 a val_a b val_b e1 || eval2 a val_a b val_b e2 
;;

let table2 a b expr = 
  [
    (true, true, eval2 a true b true expr);
    (true, false, eval2 a true b false expr);
    (false, true, eval2 a false b true expr);
    (false, false, eval2 a false b false expr) 
  ]
;;

table2 "a" "b" (And (Var "a", Or (Var "a", Var "b")));
  



