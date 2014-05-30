package f

import e.Functions.User

class PatternMatching {

  val users = e.Functions.users

  (users(0): Any) match {
    case User(_, 42) => "Probably Heisenberg"
    case User(_, x) if x % 2 == 0 => "Might be Heisenberg"
    case User("Heisenberg", _) => "That's Heisenberg"
    case User(name, id) => "This is %s with id %d" format (name, id)
    case (x,y) => "A Tuple"
    case x :: Nil => "A List with one element"
    case x :: _ => "A List"
  }

}
