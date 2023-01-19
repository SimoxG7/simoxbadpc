class Functional {

  val is_palindrome = (s:String) => {
    val s1:String = s.filterNot(x => List(',','.',';','?',' ').contains(x)).toLowerCase
    s1.equals(s1.reverse)
  }

  val is_an_anagram = (s:String, slist:List[String]) => {
    slist.map(x => x.toSeq.sorted.unwrap).filter(x => s.toSeq.sorted.unwrap).lengthIs > 1
  }

  def factors(number:BigInt, start:BigInt=2, list:List[BigInt]=Nil):List[BigInt] = {
    LazyList.iterate(start)(i => i+1)
      .takeWhile(n => n <= number)
      .find(n => number % n == 0)
      .map(n => factors(number/n, n, list :: n))
      .getOrElse(list)
  }

  val is_perfect = (n:Int) => {
    //raccoglie gli x che soddisfano quella condizione e poi li somma per vedere se è uguale a n
    ((2 until n).collect {case x if n % x == 0 => x }).sum == (n-1)
    //n-1 perchè parto da 2 e non da 1
  }

}

object Functional {
  def main(args:Array[String]) = {

    val f = new Functional

    //scala ha f strings
    List("detartrated", "Do geese see God?", "Rise to vote, sir.").map(x => f"[is_palindrome] $x :- ${f.is_palindrome(x)}\n").foreach(print(_))

    val dict = List("tar", "rat", "arc", "car", "elbow", "below", "state", "taste", "cider", "cried", "dusty", "study", "night", "thing", "inch", "chin", "brag", "grab", "cat", "act", "bored", "robed", "save", "vase", "angel", "glean", "stressed", "desserts")

    List("rat", "stressed", "elbow", "house", "thing", "desserts", "teaching").map(x => f"[is_an_anagram] $x :- ${f.is_an_anagram(x)}\n").foreach(print(_))

    List(25, 4000, 1978, 32, 32523, 7, 53, 23910201)
      .map(x => f"[factors] $x :- ${f.factors(x)}")
      .foreach(print(_))

    List(6, 7, 28, 41, 100, 496, 500, 8128).map(x => f"[is_perfect] $x :- ${f.is_perfect(x)\n}").foreach(print(_))

    

  }
}