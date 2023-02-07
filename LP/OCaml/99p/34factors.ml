(*
# factors 315;;
- : int list = [3; 3; 5; 7]
*)

let factors num = 
  let rec aux num div = match div > num with 
  | true -> []
  | false -> match num mod div with
    | 0 -> div::(aux (num/div) div)
    | n -> aux num (div+1) 
  in aux num 2
;;



