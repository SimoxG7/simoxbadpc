object Solution {

  def isPrime(n:Int):Boolean = {
    (for (num <- List.range(2, n-1) if (n % num == 0)) yield num).length == 0
  }

  def goldbach(n:Int):(Int, Int) = {

    def aux(n:Int, diff:Int):(Int, Int) = {
      (isPrime(n), isPrime(diff)) match {
        case (true, true) => (n, diff)
        case (_, _) => aux(n-1, diff+1)
      }
    }

    n%2==0 match {
      case true => aux(n-2, 2)
      case false => throw new IllegalArgumentException
    }
    
  }

  def goldbachParam(n:Int, start:Int):(Int, Int) = {

    def aux(n:Int, diff:Int):(Int, Int) = {
      (isPrime(n), isPrime(diff)) match {
        case (true, true) => (n, diff)
        case (_, _) => aux(n-1, diff+1)
      }
    }

    n%2==0 match {
      case true => aux(n-start, start)
      case false => throw new IllegalArgumentException
    }
    
  }

  def goldbachList(start:Int, end:Int):List[(Int, (Int, Int))] = {

    def aux(lst:List[Int]):List[(Int, (Int, Int))] = {
      lst match {
        case Nil => Nil
        case h::t => List((h, goldbachParam(h, 2))):::aux(t)
      }
    }

    start%2 == 0 match {
      case true => aux(List.range(start, end+1, 2))
      case false => aux(List.range(start+1, end+1, 2))
    }
  }

  def goldbachListParam(start:Int, end:Int, min:Int):List[(Int, (Int, Int))] = {

    def aux(lst:List[Int]):List[(Int, (Int, Int))] = {
      lst match {
        case Nil => Nil
        case h::t => List((h, goldbachParam(h, min))):::aux(t)
      }
    }

    start%2 == 0 match {
      case true => aux(List.range(start, end+1, 2))
      case false => aux(List.range(start+1, end+1, 2))
    }
  }

}