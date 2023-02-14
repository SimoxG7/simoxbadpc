module Natural : NaturalI.NaturalI = struct

  type natural = 
  | Zero 
  | Succ of natural 

  exception NegativeNumber

  exception DivisionByZero
  
  let rec ( + ) n = function
  | Zero -> n 
  | Succ m -> ( + ) (Succ n) m

  let rec ( > ) m n = match (m, n) with 
  | (Succ _, Zero) -> true 
  | (Zero, Succ _) -> false 
  | (Zero, Zero) -> false
  | (Succ a, Succ b) -> ( > ) a b 

  let rec ( - ) m n = if not (( > ) m n) then raise NegativeNumber 
  else match (m, n) with 
  | (a, Zero) -> a 
  | (Succ a, Succ b) -> ( - ) a b 
  | _ -> raise NegativeNumber
  
  let ( * ) m n = if m > Zero then
    let rec ( * ) r m = function
    Zero -> Zero
    | Succ(Zero) -> r
    | Succ(n) -> ( * ) ((+) r m) m n
    in ( * ) m m n
  else Zero

  let ( / ) m n = if m > Zero then (
    if not (n > Zero) then raise DivisionByZero
    else
    let rec ( / ) r m n =
    if not (n > m) then ( / ) (Succ r) ((-) m n) n
    else r
    in ( / ) Zero m n )
  else Zero

  let rec eval = function
  | Zero -> 0
  | Succ n -> succ (eval n)

  let convert n = 
    let rec convert r n = 
      if (0 < n) then convert (Succ r) (pred n)
      else r 
    in convert Zero n 

end;;

module N = (Natural: NaturalI.NaturalI);;