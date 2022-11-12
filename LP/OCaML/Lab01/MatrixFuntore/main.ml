module Integer = Matrix.Matrix(IntRing.IntRing);; 
module Boolean = Matrix.Matrix(BoolRing.BoolRing);;

let () = (* funzione sempre valutata, di fatto è un main *) 
  let z = Integer.zeroes 5 2 and i = Integer.identity 7 in 
  Integer.( + ) z i 
;;