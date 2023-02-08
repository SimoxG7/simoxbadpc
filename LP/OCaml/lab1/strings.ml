let is_alpha c = match c with 
| 'a'..'z' -> true  
| 'A'..'Z' -> true 
| _ -> false
;;

let rec to_chars = function
| [] -> []
| h::t -> match is_alpha h with 
  | true -> (Char.lowercase_ascii h)::(to_chars t)
  | false -> to_chars t
;;

let explode str = List.init (String.length str) (String.get str);;

let implode lst = String.of_seq (List.to_seq lst);;

let is_palindrome str = 
  List.rev (to_chars (explode str)) = to_chars (explode str)
;;

let rec not_in c = function
| [] -> true 
| h::t -> if c = h then false else not_in c t 
;;

let ( - ) str1 str2 = 
  implode (List.filter (fun x -> not_in x (explode str2)) (explode str1))
;;

let compare_chars a b = (Char.code a) - (Char.code b);;

let anagram s1 s2 = 
  let s1 = List.sort compare_chars (string_to_char_list_lower s1) and s2 = List.sort compare_chars (string_to_char_list_lower s2) in 
  s1 = s2 
;;











