module type StackADT = sig
  
  val empty : 'a list 
  val push : 'a -> 'a list -> 'a list
  val top : 'a list -> 'a 
  val pop : 'a list -> 'a list 

end