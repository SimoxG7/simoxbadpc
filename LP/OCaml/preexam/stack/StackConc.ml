module ListStack = struct 
  type 'a stack = 'a list

  let empty = []

  let push x s = x::s 

  let pop s = match s with 
    | [] -> failwith "empty stack"
    | _::t -> t 

  let top s = match s with 
    | [] -> failwith "empty stack"
    | h::_ -> h

  let size s = 
    let rec aux acc = function
      | [] -> acc 
      | _::t -> aux (acc+1) t 
    in aux 0 s 


end;;