package com.winbits.api.coupon.server

import akka.actor._

import spray.routing._
import spray.http._
import MediaTypes._

class RestInterface extends Actor
                    with RestApi {

  def actorRefFactory = context
  def receive = runRoute(routes)

}

trait RestApi extends HttpService {

  def routes: Route =

    path("tickets") {
      get {
        get {
          respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
            complete {
              <html>
                <body>
                  <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
                </body>
              </html>
            }
          }
        }

      }
    }

}
