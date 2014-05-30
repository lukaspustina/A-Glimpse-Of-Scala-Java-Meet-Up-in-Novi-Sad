package e

import scala.concurrent._
import ExecutionContext.Implicits.global

import scala.util.Random

object Functions {

  case class User(name: String, id: Int)

  val users = List( User("Einstein", 23), User("Heisenberg", 42) )

  object FunctionAndValues {
    // Method
    def user2Id(u: User): Int = u.id

    // just a function value
    val user2Id_2: User => Int = u => u.id

    // Create a functions value from a method
    val user2Id_3 = user2Id _
  }

  object Combinators {
    // Full syntax with combinators
    def maxUserId() = users.map(u => u.id ).max

    def maxUserId_2() = users.map(FunctionAndValues.user2Id).max

    // Syntactic sugar I
    def maxUserId_3() = users map (u => u.id) max

    // Syntactic sugar II
    def maxUserId_4() = users map (_.id) max

    def prettyPrintIds() = users map (_.id) filter (_ > 10) mkString(",")
  }

  object ViewsAndPar {
    for (u <- users.view.map (u => u.id) filter (_ % 2 == 0) ) println(u)

    users.par.map (u => u.id) filter (_ % 2 == 0) seq
  }

  object NoMoreNPE {

    // user.name might be null pointer and nobody expects it
    def getUserName(user: User): String = user.name


    def getUserName_2(user: User): Option[String] =
      if (user.name == null) return None
      else return Some(user.name)

    getUserName_2( User("Einstein", 23) ).get

    for ( name <- getUserName_2( User("Einstein", 23)) )
      println(name)
  }

  object TheFuture {
    // Pass closures and chain to next closure
    val back2TheFuture = Future {
      Random.nextInt
    } andThen {
      case i => users map (_.id) contains (i)
    }

    back2TheFuture.value
  }

}
