package com.winbits.api.coupon.server

import akka.actor._

import spray.routing._
import spray.http.{MediaTypes, StatusCodes}
import spray.httpx.SprayJsonSupport._
import spray.routing.RequestContext
import akka.util.Timeout
import scala.concurrent.duration._
import MediaTypes._


import com.winbits.api.coupon.actors.CouponDBActor
import com.winbits.api.coupon.domain.Coupon


class RestInterface extends HttpServiceActor
                    with RestApi {
  def receive = runRoute(routes)

}

trait RestApi extends HttpService with ActorLogging { actor: Actor =>
  import com.winbits.api.coupon.actors.CouponDBProtocol._
  implicit val timeout = Timeout(10 seconds)
  import akka.pattern.ask
  import akka.pattern.pipe
  import context._

  val couponDBActor = context.actorOf(Props[CouponDBActor],"couponDB_Actor")

  def routes: Route =
    path("coupon" / Segment) { couponId => requestContext =>
      val req = CouponRequest(couponId.toLong)
      val responder = createResponder(requestContext)
      couponDBActor.ask(req).pipeTo(responder)
    }

  def createResponder(requestContext:RequestContext) = {
    context.actorOf(Props(new Responder(requestContext, couponDBActor)))
  }

}

class Responder(requestContext:RequestContext, couponMaster:ActorRef) extends Actor with ActorLogging {
  import com.winbits.api.coupon.actors.CouponDBProtocol._
  import spray.httpx.SprayJsonSupport._
  import com.winbits.api.coupon.domain.CouponJson._

  def receive = {

    case Right(coupon:Coupon) =>
      log.info(s"construyendo respuesta ${coupon}")
      requestContext.complete(StatusCodes.OK, coupon)
      self ! PoisonPill

    case Left(errorMsg) =>
      requestContext.complete(StatusCodes.NotFound)
      self ! PoisonPill
  }
}
