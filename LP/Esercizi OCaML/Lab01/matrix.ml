type matrix = {l:int; h:int; mutable values: int array array}

let zeroes l h = {l = l; h = h; values = Array.make_matrix l h 0};;

let identity l = [|[|1; 0; 0|];[|0; 1; 0|];[|0; 0; 1|]|];;

let a = zeroes 2 2;;



(* type matrix = {l:int ; h:int; mutable values:(int array array)};;

(* let zeroes l h :matrix = {l = l; h = h; values = (Array.make l (Array.make h 0))};; *)

let zeroes l h = Array.make l (Array.make h 0);;

let rec iter arr s =


(* let identity s :matrix = {l = s; h = s; values = change_diag (zeroes s s) s 0} *)

let a = zeroes 2 2;;

let b = iter a 0;;
 *)




