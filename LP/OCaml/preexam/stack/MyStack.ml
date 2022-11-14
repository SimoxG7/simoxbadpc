module type MyStackADT = sig
  
  type 'a stack 
  val empty : 'a stack 
  val push : 'a -> 'a stack -> 'a stack
  val top : 'a stack -> 'a 
  val pop : 'a stack -> 'a stack 

end

module MyStack : MyStackADT = struct
  
  type 'a stack = 'a list;;
  
  let empty = [];;
  
  let push x s = x::s;;

  let pop s = match s with 
    | [] -> failwith "empty"
    | _::t -> t 
  ;;

  let top s = match s with 
    | [] -> failwith "empty"
    | h::_ -> h 
  ;;

end

