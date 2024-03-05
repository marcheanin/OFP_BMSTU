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