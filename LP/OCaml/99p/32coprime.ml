(*
# coprime 13 27;;
- : bool = true
# not (coprime 20536 7826);;
- : bool = true
*)

let rec gcd a b = match b with 
| 0 -> a 
| n -> gcd b (a mod b)
;;

let coprime a b = 
  (gcd a b) = 1
;;