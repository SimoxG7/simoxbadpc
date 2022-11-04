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

(* # coprime 13 27;;
- : bool = true
# not (coprime 20536 7826);;
- : bool = true *)

module Coprime = struct
  let coprime a b = if ((gcd a b) = 1) then true else false;;
end

open Coprime;;

coprime 13 27;;
coprime 20536 7826;;



