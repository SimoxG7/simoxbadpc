(* array sono liste ad accesso diretto, omogenei e mutabili *)
let an_array = [|1;5;10|]
an_array.(2);; (* 10 *)
an_array.(2) <- (-1);; 
an_array.(2);; (* -1 *)
an_array;; (* tutto l'array *)

(* altre operazioni sul modulo degli array *)
let a = Array.make 5 0;; (* fa array lungo 5 composto solo da 0 *)
Array.concat [a; an_array];; (* concatena due array *)

let a_matrix = Array.make_matrix 2 3 'a';; (* matrice 2x3 di 'a'*)
(*per modificare/accedere a elemento di matrice*)
a_matrix.(1).(2) <- 'z';;
a_matrix;;





