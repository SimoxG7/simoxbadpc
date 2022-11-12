(* #load "str.cma";; *)
open Str;;

let split_string s =
  Str.split (Str.regexp "[ \"()',;.!?:]") s
;;

let remove_empty list =
  let rec aux acc = function
    | [] -> acc 
    | h::t -> if (h = "") then aux acc t else aux (h::acc) t 
  in List.rev (aux [] list)
;;

let get_text = 
  let filename = "text.txt" in 
  let ic = open_in filename in 
  let try_read () = try Some (input_line ic) with End_of_file -> None in 
  let rec loop acc = match try_read () with 
    | Some s -> if (s = "") then loop acc else loop (acc@(split_string (String.lowercase_ascii s))) 
    | None -> close_in ic; acc
  in remove_empty (loop [])
;;

let rec append elem = function 
  | [] -> elem::[]
  | h::t -> append elem t
;;

(* let a = remove_empty (get_text);; *)

let fix_list list = 
  List.map split_string list
;;

(* 
let listlist_to_list ll = 
  let rec aux acc ll = match ll with 
    | [] -> acc 
    | (h::t1)::t2 -> aux (h::acc)  
    | []::t -> aux acc t 
  in List.rev (aux [] ll) 
;; *)

let b = get_text;;










