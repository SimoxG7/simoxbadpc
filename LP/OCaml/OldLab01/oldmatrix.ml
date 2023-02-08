module Matrix = struct
type matrix = int list list

let zeroes h l = List.init l (fun _ -> List.init h (fun _ -> 0));;

let identity h = List.init h (fun x -> List.init h (fun y -> if (x == y) then 1 else 0));;

let init s = List.init s (fun x -> List.init s (fun y -> x*s + y));; 

let rec transpose = function
  | [] -> []
  | []::t -> transpose t
  | (h_n::t_n)::t -> (h_n::List.map List.hd t)::(transpose (t_n :: (List.map List.tl t)))

let list_product l1 l2 =
  let rec list_product l1 l2 output = match l1, l2 with
    | h1::t1, h2::t2 -> (list_product t1 t2 (output + h1*h2))
    | _,_ -> output
  in list_product l1 l2 0
;;

let matrix_product m1 m2 = 
  let prod rowsm1 rowsm2 =
    let columns = transpose rowsm2 
  in List.map (fun row -> List.map (list_product row) columns) rowsm1
in prod m1 m2;;

end

(* type matrix = {l:int ; h:int; mutable values:(int array array)};;

(* let zeroes l h :matrix = {l = l; h = h; values = (Array.make l (Array.make h 0))};; *)

let zeroes l h = Array.make l (Array.make h 0);;

let rec iter arr s =


(* let identity s :matrix = {l = s; h = s; values = change_diag (zeroes s s) s 0} *)

let a = zeroes 2 2;;

let b = iter a 0;;
 *)




