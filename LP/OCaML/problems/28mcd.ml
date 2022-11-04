(* running example 
# gcd 13 27;;
- : int = 1
# gcd 20536 7826;;
- : int = 2 *)

let rec gcd a b = 
  if (a < b) 
  then gcd b a 
  else 
    if (a mod b = 0) 
    then b 
    else gcd b (a mod b)
;;

gcd 13 27;;
gcd 20536 7826;;


