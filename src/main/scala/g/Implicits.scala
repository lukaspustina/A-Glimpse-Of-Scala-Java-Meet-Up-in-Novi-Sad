package g

import g.Implicits.UserInt
import java.text.SimpleDateFormat
import java.util.{Date, Locale, TimeZone}

object Implicits {

  case class UserInt( name: String, id: Int )
  case class UserString( name: String, id: String )

  val usersInt = List( UserInt("Einstein", 23), UserInt("Heisenberg", 42) )
  val usersString = List( UserString("Einstein", "23"), UserString("Heisenberg", "42") )

  object SumUpIds {
    // How to sum up the ids ??
    usersInt map (_.id) sum

    usersString map (_.id) map (_.toInt) sum

    def sumUpUsers[T](users: List[T], toInt: List[T] => List[Int]): Int =  toInt(users) sum

    sumUpUsers[UserInt](usersInt, l => l map (_.id))

    sumUpUsers[UserString](usersString, l => l map (_.id) map (_.toInt))

    // Annoying to carry around the transformation functions
  }


  object Currying {
    def uncurriedFunction( p1: Int, p2: String, p3: Float) = None
    def curriedFunc( p1: Int, p2: String )( p3: Float) = None

    // uncurriedFunction == curriedFunc

    // Partially applied function
    val partial = curriedFunc(23, "Einstein") _

    val result = partial(42.0f)
  }

  object ImplicitParameters {

    implicit val userint2Int: List[UserInt] => List[Int] = l => l.map(_.id)
    implicit val userstring2Int: List[UserString] => List[Int] = l => l.map(_.id).map(_.toInt)

    def sumUpUsers[T]( l: List[T] )( implicit toInt: List[T] => List[Int] ): Int = {
      toInt(users) sum
    }

    sumUpUsers(usersInt)
    sumUpUsers(usersString)
  }


  object ImplicitTransformations {

    case class F(temperature: Double)

    val f = F(10)

    // What's that in C° ?? f.toCelsius

    implicit class FwithCelsius( val f: F ) {
      def toCelsius() =  (f.temperature - 32) * 5/9
    }

    f.toCelsius
  }

  object date {
    protected val isoDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
    isoDateTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"))

    protected val rfc822DateTimeFormat = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);

    protected val niceDateTimeFormat = new SimpleDateFormat("E, dd.MM.yyyy HH:mm", Locale.GERMAN)
    niceDateTimeFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"))

    implicit class DateWithFormat(val date: Date) {
      def asISO8601String(): String = isoDateTimeFormat format date
      def asRFC822String(): String = rfc822DateTimeFormat format date
      def asNiceString(): String = niceDateTimeFormat format date
    }
  }

  import date._

  val aDate = new Date()
  aDate.asNiceString

}
