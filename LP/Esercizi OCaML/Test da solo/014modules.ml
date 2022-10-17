(*moduli sono utilizzati per realizzare tipi di dati e collezionare funzioni*)

(*moduli si compongono di due parti:
1) (opzionale) interfaccia pubblica che espone i tipi e le operazioni definite nel modulo sig ... end 
2) implementazione del modulo struct ... end 
*)

module A:
	sig 
		(*interface*)
	end =
	struct 
		(*implementation*)
	end;;


(*modulo coda di priorita' *)
module PrioQueue =
	struct 
		type priority = int
		type char_queue = Empty | Node of priority * char * char_queue * char_queue
		exception QueueIsEmpty

		let empty = Empty
		let rec insert queue prio elt = 
			match queue with 
				Empty -> Node(prio, elt, Empty, Empty)
				| Node(p, e, left, right) -> if prio <= p then Node(prio, elt, insert right p e, left) else Node(p, e, insert right prio elt, left)

		let rec remove_top = function 
			Empty -> raise QueueIsEmpty
			| Node(prio, elt, left, Empty) -> left
			| Node(prio, elt, Empty, right) -> right
			| Node(prio, elt, (Node(lprio, lelt, _, _) as left), (Node(rprio, relt, _, _) as right)) -> if lprio <= rprio then Node(lprio, lelt, remove_top left, right) else Node(rprio, relt, left, remove_top right)
			
		let extract = function 
			Empty = raise QueueIsEmpty
			| Node(prio, elt, _, _) as queue -> (prio, elt, remove_top queue)
	end;;

(* uso: #use "014modules.ml";; *)

module type CharPQueueAbs = 
	sig 
		type priority = int
		type char_queue (*rende astratto il modulo*)
		val empty : char_queue
		val insert : char_queue -> int -> char -> char_queue
		val extract : char_queue -> int * char * char_queue
		exception QueueIsEmpty
	end;;

(* uso: #use "CharPQueueAbs.mli";; "*)
(* open CharPQueue.AbstractPrioQueue*)







