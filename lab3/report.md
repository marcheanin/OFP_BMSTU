% Лабораторная работа № 3. ‹Обобщённые классы в Scala›
% 1 апреля 2024 г.
% Марченко Андрей, ИУ9-62Б

# Цель работы
Целью данной работы является приобретение навыков разработки обобщённых классов на языке 
Scala с использованием неявных преобразований типов.

# Реализация

```scala
class MyVector[T <: Product](val elements: T){

  require(elements.productArity == 2 || elements.productArity == 3)

  def +(other: MyVector[T])(implicit ops: VectorOps[T, Int]): T={
    ops.add(elements, other.elements)
  }

  def *(other: MyVector[T])(implicit ops: VectorOps[T, Int]): Float={
    ops.scalar(elements, other.elements)
  }
  def cross(other: MyVector[T])(implicit ops: Vector3Ops[T]): T = {
    ops.cross(elements, other.elements)
  }
}

trait VectorOps[T <: Product, N] {
  def add(x: T, y: T): T
  def scalar(x:T, y:T): N
}

trait Vector3Ops[T <: Product] {
  def cross(x:T, y:T):T
}

object VectorOps {
  implicit def Dim2[N](implicit num: Numeric[N]): VectorOps[(N, N), N] = {
    new VectorOps[(N, N), N] {
      override def add(x: (N, N), y: (N, N)): (N, N) =
        (num.plus(x._1, y._1),
          num.plus(x._2, y._2))
      override def scalar(x: (N, N), y: (N, N)): N =
        num.plus(num.times(x._1,y._1), num.times(x._2,y._2))
    }
  }

  implicit def Dim3[N](implicit num: Numeric[N]): VectorOps[(N, N, N), N] = {
    new VectorOps[(N, N, N), N] {
      override def add(x: (N, N, N), y: (N, N, N)): (N, N, N) =
        (num.plus(x._1, y._1),
          num.plus(x._2, y._2),
          num.plus(x._3, y._3))
      override def scalar(x: (N, N, N), y: (N, N, N)): N =
        num.plus(num.plus(num.times(x._1,y._1),
          num.times(x._2,y._2)),
          num.times(x._3, y._3))
    }
  }
}
object Vector3Ops{
  implicit def Dim3[N](implicit num: Numeric[N]): Vector3Ops[(N, N, N)] = {
    (x: (N, N, N), y: (N, N, N)) => (
      num.minus(num.times(x._2, y._3), num.times(x._3, y._2)),
      num.minus(num.times(x._3, y._1), num.times(x._1, y._3)),
      num.minus(num.times(x._1, y._2), num.times(x._2, y._1)))
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val vec1 = new MyVector((1,1,2))
    val vec2 = new MyVector((2,2,1))
    println(vec1 * vec2)
    println(vec1 + vec2)
    println(vec1.cross(vec2))
    val vec3 = new MyVector((1, 2))
    val vec4 = new MyVector((5, 6))
    println(vec3 * vec4)
    println(vec3 + vec4)

    // val vec4 = new MyVector((1, 2, 3, 4))
  }
}


```

# Вывод программы

```
6.0
(3,3,3)
(-3,3,0)
17.0
(6,8)
```

# Вывод
Эта лабораторная оказалась для меня непростой, я долго разбирался в шаблонизации в Scala,
однако в итоге всё получилось, и лабораторная не показалась такой сложной.
В заключении, я считаю достигнутой цель лабораторной работы.