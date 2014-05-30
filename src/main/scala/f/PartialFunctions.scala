package f

object PartialFunctions {

  // andThen

  // Sealed Case Class
  sealed abstract class ReturnCode(code: Int)
  case object OK extends ReturnCode(200)
  case object Created extends ReturnCode(201)
  case object ServerError extends ReturnCode(501)

  case class Response( returnCode: ReturnCode, value: String)

  // Response => Either[String, String]
  type Handler = PartialFunction[Response, Either[String, String]]

  object OkayHandler {
    val okay: Handler = {
      case Response(OK, value) => Right(value)
    }

    val catchAll: Handler = {
      case Response(_, value) => Left(value)
    }

    val response = Response(OK, "<html> .... </html>")

    (okay orElse catchAll)(response) match {
      case Right(s) => "All fine: %s" format s
      case Left(_) => "Something went wrong"
    }
  }

  object CreatedHandler {
    import OkayHandler._

    val created: Handler = {
      case Response(Created, value) => Right(value)
    }

    (okay orElse created orElse catchAll)(response) match {
      case Right(s) => "All fine"
      case Left(_) => "Something went wrong"
    }
  }

}
