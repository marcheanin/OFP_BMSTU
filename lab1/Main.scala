object Main {

  private val lists_rec : (List[Int], Int, Int) => List[(Int, Int)] = {
    case (Nil, first, prev) => List((first, prev)) // если дощли до конца списка
    case (x :: xs, first, prev) if(x == prev + 1) =>
      lists_rec(xs, first, x) // если текущий элемент равен предыдущему + 1, продолжаем копить
    case (x :: xs, first, prev) if(x != prev + 1) =>
      List((first, prev)) ::: lists_rec(xs, x, x) // если цепочка закончена, добавляем в конец и начинаем новую
  }

  private val lists: List[Int] => List[(Int, Int)] = {
    case Nil => Nil
    case x :: xs => lists_rec(xs, x, x)
  }

  def main(args: Array[String]): Unit = {
    println(lists(List(1, 2, 3, 5, 7, 8, 9, 10, 16, 18)))
    println(lists(List()))
    println(lists(List(1)))
  }
}