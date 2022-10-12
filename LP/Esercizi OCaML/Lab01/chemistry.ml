let alkaline_earth_metals = ("beryllium", 4)::("magnesium", 12)::("calcium", 20)::("strontium", 38)::("barium", 56)::("radium", 88)::[] ;; (* lista di tuple string * int*)


let noble_gases = [("helium", 2); ("neon", 10); ("argon", 18); ("krypton", 36); ("xenon", 54); ("radon", 86)];;

(* rinomino operatore, lavora su coppie *)
let (>:) a b = (snd a) - (snd b);;
let max a b = if (a >: b >= 0) then a else b;;

(* scorriamo la lista confrontando i pesi atomici e ci teniamo da parte il più pesante *)
let heaviest lst =
  (* fold_left è funzione di due argomenti che usa per aggregare *)
  List.fold_left max (List.hd lst) (List.tl lst);; (* uso head e tail della lista *)

let sort_ascending lst = List.sort (>:) lst;;

let merge_elements metals gases = 
  let sorted_metals = sort_ascending metals and sorted_gases = sort_ascending gases in List.merge (>:) sorted_metals sorted_gases;;

merge_elements alkaline_earth_metals noble_gases;;


(* fare il main *)


    

  