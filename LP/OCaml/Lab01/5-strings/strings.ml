let string_to_char_list s = 
  let rec aux acc i s = 
    if (i = 0) then acc else aux ((String.get s 0)::acc) (i-1) (String.sub s 1 ((String.length s) -1))
  in List.rev (aux [] (String.length s) s) 
;;

let a = string_to_char_list "Simox";;

let to_lower c = match c with 
  | 'a'..'z' -> c 
  | 'A'..'Z' -> Char.chr ((Char.code c) + 32)
  | _ -> failwith "error"
;;

let is_alpha c = match c with 
  | 'a'..'z' -> true
  | 'A'..'Z' -> true 
  | _ -> false
;;

let remove_non_alpha charlist = 
  let rec aux acc = function
  | [] -> acc 
  | h::t -> if (is_alpha h) then aux ((to_lower h)::acc) t else aux acc t 
  in List.rev (aux [] charlist)
;;

let is_palindrome s =
  let charlist = remove_non_alpha (string_to_char_list s) in  
  let l2 = List.rev charlist in 
  l2 = charlist
;;

is_palindrome "abcba";;

let b = remove_non_alpha (string_to_char_list "Simox!!xoMIS");;

let c = is_palindrome "SIMOX!!!???32331Xomis";;


let compare_chars a b = (Char.code a) - (Char.code b);;

let string_to_char_list_lower s = 
  let rec aux acc i s = 
    if (i = 0) then acc else aux ((to_lower (String.get s 0))::acc) (i-1) (String.sub s 1 ((String.length s) -1))
  in List.rev (aux [] (String.length s) s) 
;;

let ( - ) s1 s2 = 
  let rec aux s1 s2 = match s2 with 
    | "" -> s1
    | _ -> aux (Str.(global_replace (regexp (String.make 1 (String.get s2 0))) "" s1)) (String.sub s2 1 ((String.length s2) -1))
  in aux s1 s2
;;

let d = ( - ) "Simox" "xoSI";;


let anagram s1 s2 = 
  let s1 = List.sort compare_chars (string_to_char_list_lower s1) and s2 = List.sort compare_chars (string_to_char_list_lower s2) in 
  s1 = s2 
;;

let e = anagram "Simox" "xomis";;

let rec anagram_list s list = match list with 
  | [] -> false 
  | h::t -> if (anagram h s) then true else anagram_list s t 
;;

let f = ["Simox"; "Sam"; "XD"; "Hello"; "ocamlsucks"; "porcodio"];;

let g = anagram_list "dioporco" f;;

let h = anagram_list "X" f;;





