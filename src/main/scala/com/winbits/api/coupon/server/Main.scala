package com.winbits.api.coupon.server

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import akka.io.IO
import spray.can.Http

/**
 * Created by manuel on 3/25/14.
 */
object Main extends App {

  implicit val system = ActorSystem("on-spray-can")

  val config = ConfigFactory.load()
  val host = config.getString("service.host")
  val port = config.getInt("service.port")

  val service = system.actorOf(Props(new RestInterface()), "httpInterface")
  IO(Http) ! Http.Bind(service, interface = host, port = port)
}
