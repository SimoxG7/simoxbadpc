module Primality = struct
  let trialdivision n = 
    let rec aux div n = match div >= n with 
    | true -> true 
    | false -> match n mod div = 0 with 
      | true -> false 
      | false -> aux (div+1) n 
    in aux 2 n
  ;;  

  let lucaslehmer m = 
    let rec aux p s m = if p = 0 then s = 0 
    else aux (p-1) ((s*s-2) mod m) m 
    in aux ((int_of_float ((log ((float m)+. 1.))/.(log 2.)))-2) 4 m 
  ;;

  
  let littlefermat p = true;;
    (* (List.length (List.filter (fun y -> (modexp y (p-1) p) <> 1)
    (range ~step:3 2 (int_of_float (log (float p))+1)))
    == 0);; *)

  let is_prime n = 

end;;