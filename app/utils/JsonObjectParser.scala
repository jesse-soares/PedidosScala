package utils

import com.codahale.jerkson.Json
 
import play.api.Play
import play.api.mvc._
import BodyParsers.parse.DEFAULT_MAX_TEXT_LENGTH
import play.api.libs.iteratee._
import play.api.libs.iteratee.Input._
 
class JsonObjectParser[A : Manifest] extends BodyParser[A] { 
  def apply(request: RequestHeader): Iteratee[Array[Byte], Either[Result, A]] = {
    Traversable.takeUpTo[Array[Byte]](DEFAULT_MAX_TEXT_LENGTH).apply(Iteratee.consume[Array[Byte]]().map { bytes =>
    scala.util.control.Exception.allCatch[A].either {
      Json.parse[A](new String(bytes, request.charset.getOrElse("utf-8")))
    }.left.map { e =>
      (Play.maybeApplication.map(_.global.onBadRequest(request, "Invalid Json")).getOrElse(Results.BadRequest), bytes)
    }
  }).flatMap(Iteratee.eofOrElse(Results.EntityTooLarge))
    .flatMap {
      case Left(b) => Done(Left(b), Empty)
      case Right(it) => it.flatMap {
        case Left((r, in)) => Done(Left(r), El(in))
        case Right(a) => Done(Right(a), Empty)
      }
    }
  }
}