(*   
# gcd 13 27;;
- : int = 1
# gcd 20536 7826;;
- : int = 2
*)

let rec gcd n1 n2 = match n2 with 
| 0 -> n1 
| n -> gcd n2 (n1 mod n2)
;;
