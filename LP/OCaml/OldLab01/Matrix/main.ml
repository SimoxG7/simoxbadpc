module TheMatrix = (Matrix.Matrix : MatrixADT.Matrix) ;;
open TheMatrix;;

let () = (* funzione sempre valutata, di fatto è un main *) 
  let z = zeroes 5 2 and i = identity 7 and a = init 7 in 
  let c = a + i in 
  let t = transpose c in 
  let p = t*i in ()
;;