(* in terminale: #load "str.cma";; *)

open Str 

let filename = "prova.txt";;

let rec list_to_one_string s l = match l with 
  | [] -> s 
  | h::t -> list_to_one_string (s^h^" ") t
;; 

let split_string s =
  Str.split (Str.regexp " ") s
;;

let read_lines filename : string list = 
  let ic = open_in filename in 
    let try_read () = 
      try Some (input_line ic) with End_of_file -> None in 
      let rec loop acc = match try_read () with 
        | Some s -> loop (s::acc)
        | None -> close_in ic; List.rev (split_string (list_to_one_string "" acc)) in 
      loop []
;;

let is_alpha alpha = 
  match alpha with 
    'a' .. 'z' -> true
  | 'A' .. 'Z' -> true 
  | _ -> false;;


(* open Str 

let filename = "prova.txt";;

let read_lines filename : string list = 
  let ic = open_in filename in 
    let try_read () = 
      try Some (input_line ic) with End_of_file -> None in 
      let rec loop acc = match try_read () with
        | Some s -> loop (s::acc)
        | None -> close_in ic; List.rev acc in 
      loop []
;;



let split_string s =
  Str.split (Str.regexp " ") s
;;

let rec map f l =
  match l with 
    | [] -> []
    | h::t -> (f h)::(map f t)
;; 

let rec iter = function
  | h::t -> h; iter t
  | [] -> "" 
;;

let rec list_to_one_string s l = match l with 
  | [] -> s 
  | h::t -> list_to_one_string (s^h^" ") t
;; 

let rec append l1 l2 =
  match l1 with 
    | [] -> l2
    | h::t -> h::(append t l2)
;;


 

let b = ["Hello Simox"; "Goodbye world"];;
map split_string b;;
let c = [["Hello Simox"; "Goodbye world"];["No help"; "You will die"]];;



list_to_one_string "" b;;




(* let in_channel = open_in filename in
  try 
    let rec line = input_line in_channel in
      print_endline line;
      line 
    close_in in_channel;
  with e -> 
    close_in_noerr in_channel;
    let fail_error = "There is no file \"" ^ filename ^ "\"" in failwith fail_error
;; *)


  



 *)
