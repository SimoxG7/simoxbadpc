import collection.mutable

object Solution {
  def wordPattern(pattern: String, str: String): Boolean = {
    val words = str.split(' ')
    if (words.length != pattern.length) return false
    val patternIndex = mutable.HashMap[Char, Int]()
    val wordIndex = mutable.HashMap[String, Int]()
    !words.indices.exists(i => patternIndex.put(pattern(i), i) != wordIndex.put(words(i), i))
  }
}