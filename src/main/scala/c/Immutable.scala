package c

object Immutable {

  object Vars {
    val immutable = 1
    //immutable += 1

    var mutable = 1
    mutable += 1
  }

  object Lists {
    var l = 1 :: 2 :: Nil

    val l2 = l

    l = 10 :: l
    // l2 remains the same old list
  }

  object Sequences {
    val s = Seq(1,2,3,4)
  }

  object MutableSequence {
    val s = scala.collection.mutable.Seq(1,2,3,4)
    s :+ 5
    val b = scala.collection.mutable.Buffer(1,2,3,4)
    b += 5
  }

}

