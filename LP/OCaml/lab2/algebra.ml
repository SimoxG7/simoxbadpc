module type MonoidADT = sig
  type t 
  val set:t list 
  val i:t 
  val op:t -> t -> t 
end;;

module BoolMonoid : MonoidADT = struct
  type t = bool
  let set = [true; false]
  let i = false
  let op a b = a || b
end;;

module Z5Monoid : MonoidADT = struct
  type t = int 
  let set = [0;1;2;3;4]
  let i = 0
  let op a b = (a + b) mod 5 
end;;

module type GroupADT = sig
  type t 
  val set:t list 
  val i:t 
  val op: t -> t -> t
  val inverse: t -> t 
  val test_associativity: t -> t -> t -> bool  
end;;

module BoolGroup : GroupADT = struct
  type t = bool 
  let set = [false; true]
  let i = false
  let op a b = a || b 
  let inverse a = List.find (fun x -> x = not a) set 
  let test_associativity a b c = (op (op a b) c) = (op a (op b c)) 
end;;

module Z100Group : GroupADT = struct (* neg e pos *)
  type t = int 
  let set = List.init 201 (fun x -> x -101)
  let i = 0
  let op a b = a + b 
  let inverse a = List.find (fun x -> x = (-(a))) set
  let test_associativity a b c = (op (op a b) c) = (op a (op b c)) 
end;;

module Z4Group : GroupADT = struct
  type t = int 
  let set = List.init 4 (fun x -> x)
  let i = 1
  let op a b = (a * b) mod 4 
  let inverse a = a (* servirebbe eulero, poco sbatti di farlo *)
  let test_associativity a b c = (op (op a b) c) = (op a (op b c)) 
end;;

module type RingADT = sig
  type t 
  val set:t list 
  val i:t
  val op1: t -> t -> t 
  val op2: t -> t -> t 
  val test_commutative_op1: t -> t -> bool 
  val test_distributivity_op2: t -> t -> t -> bool 
end;;

module ZeroRing : RingADT = struct 
  type t = int 
  let set = [0]
  let i = 0
  let op1 a b = a+b 
  let op2 a b = a*b 
  let test_commutative_op1 a b = (op1 a b) = (op1 b a)
  let test_distributivity_op2 a b c = op2 a (op1 b c) = op1 (op2 a b) (op2 a c) 
end;;

module ZRing : RingADT = struct
  type t = int 
  let set = List.init 201 (fun x -> x-101)
  let i = 0
  let op1 a b = a+b 
  let op2 a b = a*b 
  let test_commutative_op1 a b = (op1 a b) = (op1 b a)
  let test_distributivity_op2 a b c = op2 a (op1 b c) = op1 (op2 a b) (op2 a c) 
end;;

module Z4Ring : RingADT = struct
  type t = int 
  let set = [0;1;2;3]
  let i = 0
  let op1 a b = (a + b) mod 4 
  let op2 a b = (a * b) mod 4 
  let test_commutative_op1 a b = (op1 a b) = (op1 b a)
  let test_distributivity_op2 a b c = op2 a (op1 b c) = op1 (op2 a b) (op2 a c) 
end;;












