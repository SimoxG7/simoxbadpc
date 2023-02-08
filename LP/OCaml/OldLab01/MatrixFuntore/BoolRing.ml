module BoolRing = 
struct
  type t = bool 
  let zero = false 
  let one = true 
  let ( + ) x y = x || y 
  let ( * ) x y = x && y
end