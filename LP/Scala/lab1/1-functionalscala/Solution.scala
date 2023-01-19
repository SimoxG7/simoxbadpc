object Solution {

  def is_palindrome(str:String):Boolean = {
    str.toLowerCase == str.reverse.toLowerCase
  }

  def is_anagram(str:String, strlist:List[String]):Boolean = {
    strlist match {
      case Nil => false
      case h::t => h.sorted.equals(str.sorted) match {
        case true => true 
        case false => is_anagram(str, t)
      }
    }
  }

  def factors(num:Int):List[Int] = {
    
    def aux(num:Int, div:Int):List[Int] = {
      num match {
        case 1 => Nil 
        case _ => num % div == 0 match {
          case true => List(div):::aux(num/div, div)
          case false => aux(num, div+1)
        } 
      }
    }

    aux(num, 2)
  }

  def is_proper(num:Int):Boolean = {

    val lst = for (div <- List.range(1, num-1) if num % div == 0) yield div

    lst.fold(0)((x, y) => x+y) == num
  }

  def generate_propers(bound:Int):List[Int] = {
    for (num <- List.range(2, bound+1) if(is_proper(num))) yield num 
  }

}