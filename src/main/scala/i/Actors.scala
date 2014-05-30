package i

import akka.actor.{Props, ActorSystem, Actor}

object Actors {

  case class Greeting(who: String)

  class MyActor extends Actor {
    def receive = {
      case Greeting(who) =>
        println(who)
      case "Stop" =>
        context stop self
      case "Go to hell" =>
        throw new RuntimeException
    }
  }

  val system = ActorSystem("MySystem")
  val greeter = system.actorOf(Props[MyActor], name = "greeter")
  greeter ! Greeting("Einstein")
  greeter ! "Stop"

}
