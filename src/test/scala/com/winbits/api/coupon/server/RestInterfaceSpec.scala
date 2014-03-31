package com.winbits.api.coupon.server

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

/**
 * Created by manuel on 3/31/14.
 */
class RestInterfaceSpec extends Specification with Specs2RouteTest  {
  def actorRefFactory = system

  "RestInterface" should {

    "return abc for GET request" in {
      Get("coupon/1") ~> routes ~> check {
        responseAs[String] must contain("abc")
      }
    }
  }

}
