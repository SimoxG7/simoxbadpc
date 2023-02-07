type 'a rle =
  | One of 'a
  | Many of int * 'a

(* # encode ["a"; "a"; "a"; "a"; "b"; "c"; "c"; "a"; "a"; "d"; "e"; "e"; "e"; "e"];;
- : string rle list =
[Many (4, "a"); One "b"; Many (2, "c"); Many (2, "a"); One "d";
 Many (4, "e")] *)

let encode l =
  let create_tuple cnt elem = match cnt with 
    | 1 -> One elem
    | n -> Many (cnt, elem) in
  let rec aux count acc = function
    | [] -> []
    | [x] -> (create_tuple (count + 1) x) :: acc
    | a::(b::_ as t) -> match a = b with 
      | true -> aux (count+1) acc t 
      | false -> aux 0 ((create_tuple (count+1) a)::acc) t in 
  List.rev (aux 0 [] l)
;;




