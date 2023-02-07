let rec gcd a b = match b with 
| 0 -> a 
| n -> gcd b (a mod b)
;;

let coprime a b = 
  (gcd a b) = 1
;;

let phi n = 
  List.length(List.filter (fun x -> coprime x n ) (List.init (n-1) (fun x -> x+1)))
;;