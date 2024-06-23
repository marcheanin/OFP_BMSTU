% Лабораторная работа № 1 «Введение в функциональное
  программирование на языке Scala»
% 4 марта 2024 г.
% Андрей Марченко, ИУ9-62Б

# Цель работы
Целью данной работы является изучение базовых объектно-ориентированных возможностей языка Scala.

# Индивидуальный вариант
Элемент свободной группы с двумя образующими. Представляет собой либо
пустое слово (единица группы), либо конечное слово, составленное из
четырёх символов a, ã, b, b̃ таким образом, что в нём a не появляется
рядом с ã, а b не появляется рядом с b̃. Операция сложения двух слов
определяется как их конкатенация с последующим сокращением пар aã,
ãa, bb̃ и b̃b. Операция взятия обратного элемента — как
переворачивание слова с одновременной заменой a на ã, ã — на a, b —
на b̃ и b̃ — на b.

# Реализация и тестирование

В моей реализации ã = A, b̃ = B

```scala
object Main {
  private class FreeGroup (_word: String) {
    val word: String = setWord(_word)

    private def reduce(_word : String): String = {
      var len = 0
      var res = _word
      while (len != res.length) {
        len = res.length
        res = res.replaceAll("aA", "")
        res = res.replaceAll("Aa", "")
        res = res.replaceAll("Bb", "")
        res = res.replaceAll("bB", "")
      }
      res
    }

    private def setWord (_word: String) : String = {
      val pattern = "^[aAbB]*$".r
      if (pattern.findFirstMatchIn(_word).isDefined) reduce(_word)
      else ""
    }

    def +(word2: FreeGroup): FreeGroup = {
      new FreeGroup(setWord(word.concat(word2.word)))
    }

    def unary_-(): FreeGroup = {
      var revWord = word.reverse
      revWord = revWord.map {
        case c if c.isLower => c.toUpper
        case c if c.isUpper => c.toLower
      }
      new FreeGroup(revWord)
    }

    override def toString: String = word

    def this() = this("")
  }
  def main(args: Array[String]): Unit = {
    val w = new FreeGroup("aAAbBabbBBb")
    val v = new FreeGroup("aBBaB")
    println(w.word)
    println(w + v)
    println(-v)
    val wrong_word = new FreeGroup("ofp")
    println(wrong_word.word.length)
    println(v + "")
    println("" + new FreeGroup("ABba"))
  }
}
```

Вывод программы:

```scala
b
baBBaB
bAbbA
0
aBBaB

```

# Вывод
В этой лабораторной работе я освоил базовые навыки по написанию объектно-ориентированных программ
 на языке Scala: понял структуру классов,
правила написания конструкторов и описания операторов, тем самым цель
работы считаю достигнутой. Также, на мой взгляд, мне попался один из самых
интересных вариантов, что смотивировало меня сделать работу в тот же день.