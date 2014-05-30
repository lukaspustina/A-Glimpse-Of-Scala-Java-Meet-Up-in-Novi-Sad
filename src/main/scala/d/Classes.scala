package d

object Classes {

  object Canonical {

    // Getter and Setter
    class User(var name: String, id: Int) {

      val myId = id

      def userId() = myId

      override def equals(obj: scala.Any): Boolean = super.equals(obj)
      override def hashCode(): Int = super.hashCode()
      override def toString: String = super.toString
    }

    val u1 = new User("Einstein", 23)

    // Companion object
    object User {
      protected var counter = 0

      protected def id(): Int = {
        counter += 1
        counter
      }

      def apply(name: String): User = new User(name, id)
    }

    val u2 = User("Heisenberg")
  }



  object Case {
    case class User(name: String, id: Int)

    val u = User("Einstein", 23)
  }

}
