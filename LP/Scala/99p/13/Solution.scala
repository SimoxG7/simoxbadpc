object SOlution {

  def encodeDirect[A](lst: List[A]): List[(Int, A)] =
    val (packed, next) = lst.span(_ == lst.head)
    (packed.length, packed.head) :: encodeDirect(next)
}
