module type Matrix =
sig 
    type elt 
    type matrix 
    val zeroes : int -> int -> matrix
    val identity : int -> matrix 
    val transpose : matrix -> matrix
    val ( + ) : matrix -> matrix -> matrix 
    val ( * ) : matrix -> matrix -> matrix 
end;;
