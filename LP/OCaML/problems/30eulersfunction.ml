module MCD = struct
  let rec gcd a b = 
    if (a < b) 
    then gcd b a 
    else 
      if (a mod b = 0) 
      then b 
      else gcd b (a mod b)
  ;;
end

open MCD;;

module Coprime = struct
  let coprime a b = if ((gcd a b) = 1) then true else false;;
end

open Coprime;;

module Euler = struct
  let euler a = 
    let rec aux a acc curr = if (a = 1) then 1 else if (curr = a) then acc else  
      if (coprime a curr) then aux a (acc+1) (curr+1) else aux a acc (curr+1)
    in aux a 0 1;;
  ;;
end

open Euler;;

let a = euler 10;;



