object Solution {
  def detectCapitalUse(word: String): Boolean = {
    word.toUpperCase == word || 
    word.toLowerCase == word || 
    (word.head.toUpper.toString + word.tail.toLowerCase == word)
  }
}

/*
object Solution {

  def detectCapitalUse(word: String): Boolean = {
    val lst = word.toList

    def aux(lst:List[Char], firstUpper:Boolean, countUpper:Int, len:Int):Boolean = {
      lst match {
        case h::t => h.isUpper match {
          case true => aux(t, firstUpper, countUpper+1, len)
          case false => aux(t, firstUpper, countUpper, len)
        }
        case Nil => (firstUpper, countUpper) match {
          case (true, 1) => true 
          case (true, _) => countUpper == len
          case (false, 0) => true
          case (_, _) => false
        }
      }
    }

    aux(lst, lst.head.isUpper, 0, lst.length)
  }
}
*/
