(* 
# extract 2 ["a"; "b"; "c"; "d"];;
- : string list list =
[["a"; "b"]; ["a"; "c"]; ["a"; "d"]; ["b"; "c"]; ["b"; "d"]; ["c"; "d"]] *)

let extract list = 
  let rec getn n acc bigacc = function
  | [] -> (List.rev acc)::bigacc
  | h::t -> if (n = 0) then (List.rev acc)::bigacc else getn (n-1) (h::acc) bigacc t
  in  


let a = ["a"; "b"; "c"; "d"];;
let b = extract 2 a;;