(*   
# range 4 9;;
- : int list = [4; 5; 6; 7; 8; 9]
*)

let rec range s e = match s = e with 
| true -> [e]
| false -> s::(range (s+1) e) 
;;

