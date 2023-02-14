let range ?step:(s=1) i j =
  let rec range' n acc =
    if n > j then (List.rev acc) else range' (n+s) (n::acc)
  in range' i [] ;;
  
let trialdivision x =
  Printf.printf "Trial-Division's Primality Test\t" ;
  (List.length(List.filter(fun y -> (x mod y) == 0)(range 2 (int_of_float (sqrt (float x))+1))) == 0);;


(* modular exponent *)
let modexp b e m =
  let rec modexp' c b e' e m =
    if e' <= e then modexp' ((c*b) mod m) b (e'+1) e m
    else c
  in modexp' 1 b 1 e m ;;
  
(* lucas-lehmer test of primality. *)
(* m is the prime of Marsenne, i.e., 2^p-1 where p is an odd prime *)
let lucaslehmer m =
  Printf.printf "Lucas-Lehmer's Primality Test\t" ;
  let rec lucaslehmer p s m =
    if p==0 then s==0
    else lucaslehmer (p-1) ((s*s-2) mod m) m
  in lucaslehmer ((int_of_float ((log ((float m)+.1.))/.(log 2.)))-2) 4 m ;;

let littlefermat p =
  Printf.printf "Little Fermat's Primality Test\t" ;
  (List.length(List.filter(fun y -> (modexp y (p-1) p) <> 1) (range ~step:3 2 (int_of_float (log (float p))+1))) == 0);;
  
let is_prime x =
  let criteria = [
  ((fun y -> y<10000), trialdivision);
  ((fun y -> y<=524287), lucaslehmer);
  ((fun y -> true), littlefermat)] in
  let rec is_prime' x = function
    (p,t)::tl -> if (p x) then (t x) else is_prime' x tl
    | [] -> false
  in is_prime' x criteria ;;