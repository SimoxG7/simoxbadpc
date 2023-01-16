object Solution {

  def goldbach(num:Int):(Int, Int) = {

    def is_prime(num:Int):Boolean = {
      (for (div <- List.range(2, num) if (num % div == 0)) yield div).length == 0
    }

    def generate_primes(bound:Int):List[Int] = {
      for (num <- List.range(2, bound) if (is_prime(num))) yield num 
    }

    def aux(num:Int, lst:List[Int]):(Int, Int) = {
      lst match {
        case Nil => throw new NoSuchElementException
        case h::t => is_prime(num-h) match {
          case true => (h, num-h)
          case false => aux(num, t)
        }
      }
    }

    num % 2 == 0 match {
      case true => aux(num, generate_primes(num))
      case false => throw new IllegalArgumentException("Only even numbers allowed")
    }
  }

  def goldbach_list(start:Int, end:Int):List[(Int, (Int, Int))] = {
    for (num <- List.range(start, end+1) if (num % 2 == 0)) yield (num, goldbach(num))
  }
}