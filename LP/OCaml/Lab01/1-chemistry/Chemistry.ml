type element = string * int;;

let aem = [("magnesium", 12); ("barium", 56); ("beryllium",4); ("radium", 88); ("calcium", 20); ("strontium", 38)];;

let heaviest list = 
  let rec aux res = function
    | [] -> res 
    | h::t -> if (snd h > snd res) then aux h t else aux res t 
  in aux ("", 0) list
;;

let heav = heaviest aem;; 

let compare elem1 elem2 = (snd elem1) - (snd elem2);;

let sort list = List.sort (fun x y -> (snd x) - (snd y)) list;;

let aem_sorted = sort aem;;

let ng = [("helium", 2); ("neon", 10); ("argon", 18); ("krypton", 36); ("xenon", 54); ("radon", 86)];;

let merge_lists list1 list2 = 
  let rec aux list1 list2 acc = match list1 with 
    | [] -> (List.rev acc)@list2 
    | h::t -> aux t list2 (h::acc) 
  in sort (aux list1 list2 [])
;;

let all = merge_lists aem ng;;










 