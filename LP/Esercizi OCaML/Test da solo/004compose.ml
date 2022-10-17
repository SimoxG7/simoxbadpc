(** 3 arguments *)
let compose f g x = f (g x);;
(** 2 arguments *)
let compose' (f, g) x = f (g x);;
let succ = fun x -> x+1;;

let plus1 = compose succ;;
(** this won't work because compose' has 2 arguments (a couple and a single) *)
let plus1' = compose' succ;;

let plus2 = plus1 succ;;
let plus2' = compose'(succ, succ);;

plus2 7;;
plus2' 7;;



