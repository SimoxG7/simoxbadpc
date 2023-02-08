type element = {name : string; atomic : int};;

let alkaline_earth_metals = [{name = "beryllium"; atomic = 4}; {name = "magnesium"; atomic = 12}; {name = "calcium"; atomic = 20}; {name = "strontium"; atomic = 38}; {name = "barium"; atomic = 56}; {name = "radium"; atomic = 88}];;

let noble_gases = [{name = "helium"; atomic = 2}; {name = "neon"; atomic = 10}; {name = "argon"; atomic = 18}; {name = "krypton"; atomic = 36}; {name = "xenon"; atomic = 54}; {name = "radon"; atomic = 86}];;

let highest_metal lst = 
  let rec aux curr = function
  | [] -> curr 
  | h::t -> match h.atomic > curr.atomic with 
    | true -> aux h t 
    | false -> aux curr t 
  in aux {name = "default"; atomic = 0} lst 
;;

let sort_metals lst = List.sort (fun x y -> x.atomic - y.atomic) lst;;

let merge lst1 lst2 = sort_metals (lst1@lst2);;






