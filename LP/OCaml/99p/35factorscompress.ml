(*
# factors 315;;
- : (int * int) list = [(3, 2); (5, 1); (7, 1)]
*)

let factors_compressed num = 
  let rec aux cont num div = match div > num with 
    | true when cont <> 0 -> (div, cont)::[]
    | true -> []
    | false -> match num mod div with 
      | 0 -> aux (cont+1) (num/div) div 
      | n -> match cont with 
        | 0 -> aux 0 num (div+1)
        | o -> (div, cont)::(aux 0 num (div+1))  
  in aux 0 num 2
;;
        






