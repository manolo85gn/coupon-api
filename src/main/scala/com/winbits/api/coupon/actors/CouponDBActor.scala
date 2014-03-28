package com.winbits.api.coupon.actors

import akka.actor.{PoisonPill, Actor}
import com.winbits.api.coupon.dao.CouponDAO
import akka.actor._
import concurrent.Future
import scala.concurrent.duration._
import akka.util.Timeout

/**
 * Created by manuel on 3/26/14.
 */
object CouponDBProtocol {
  import spray.json._
  import DefaultJsonProtocol._

  case class CouponRequest(id:Long)

  //----------------------------------------------
  // JSON
  //----------------------------------------------
  object CouponRequest extends DefaultJsonProtocol {
    implicit val format = jsonFormat1(CouponRequest.apply)
  }

}

class CouponDBActor extends Actor with ActorLogging {
  import CouponDBProtocol._
  import context._
  implicit val timeout = Timeout(5 seconds)

  def receive: Receive = {
    case CouponRequest(id:Long) =>
      log.info(s"Getting a ticket for the ${id} event.")
      sender ! CouponDAO.get(id)
  }

}
