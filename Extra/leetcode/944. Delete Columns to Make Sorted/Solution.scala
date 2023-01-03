object Solution {
  def minDeletionSize(strs: Array[String]): Int = {

    def aux(strs:Array[String], cont:Int, indexStart:Int, indexEnd:Int, charIndex:Int):Int = {
      (indexStart == indexEnd) match {
        case true => {
          charIndex == strs.length-1 match {
            case true => cont
            case false => aux(strs, cont+1, 0, strs.length-1, charIndex+1)  
          }
        }
        case false => {
          strs(indexStart).charAt(charIndex) < strs(indexEnd).charAt(charIndex) match {
            case true => aux(strs, cont, indexStart, index)
          }
        } 
      }
    }

    /*
    strs(0).charAt(i) < strs(strs.length-1).charAt(i) 
    case true => strs(0).charAt(i) < strs(strs.length-2).charAt(i)
    case false => inc cont, pass to next index
    */

  }
}