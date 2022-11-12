module Matrix (R : Ring.Ring) : (MatrixADT.Matrix with type elt = R.t) = 
struct
  type elt = R.t;; 
  type matrix = elt list list;;
  
  let zeroes n m = 
    List.init n (fun x -> List.init m (fun x -> R.zero) )
  ;; (* funzione interna fa una nuova list init*)
  
  let identity n = 
    List.init n (fun x -> List.init n (fun y -> if (x <> y) then R.zero else R.one))
  ;;

  let rec transpose = function
    | [] -> [] 
    | []::xss -> transpose xss
    | (x::xs)::xss -> (x::List.map List.hd xss)::transpose (xs::List.map List.tl xss) (* testa, con conseguente e concatenata a una lista. prendo testa della lista su cui sto lavorando e poi vado a concatenare con tutte le altre teste di ss*)
  ;;

  let vect_prod v1 v2 = 
    let rec aux res v1 v2 = match v1,v2 with 
      | h1::t1, h2::t2 -> aux (R.( + ) res (R.( * ) h1 h2)) t1 t2 (* pensa di dover usare il + tra matrici!*)
      | _, _ -> res 
    in aux R.zero v1 v2
  ;;

  let ( + ) m1 m2 = 
    List.map2 (fun r1 r2 -> List.map2 (fun v1 v2 -> (R.( + ) v1 v2)) r1 r2) m1 m2 (*mappa due liste*)
  ;;

  let ( * ) m1 m2 = (* elem i,j è data dal prodotto della riga i per la riga j *)
    let rec matprod rows1 rows2 = 
      let cols = transpose rows2 in 
      List.map (fun row -> List.map (vect_prod row) cols) rows1 
    in matprod m1 m2
  ;;

end;;




