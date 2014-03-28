package com.winbits.api.coupon.domain

import scala.slick.driver.MySQLDriver.simple._
import spray.json._
import DefaultJsonProtocol._
/**
 * Created by manuel on 3/26/14.
 */
case class Coupon(id: Long,
                  code: String)

object CouponJson extends DefaultJsonProtocol {

    implicit object CouponJsonFormat extends RootJsonFormat[Coupon] {
      def write(t: Coupon) = JsObject(
        "id"   -> JsNumber(t.id.toLong),
        "code"  -> JsString(t.code.toString())
      )

      def read(j: JsValue) = {
        j.asJsObject.getFields("id", "code") match {
          case Seq(JsNumber(id), JsString(code)) =>
            new Coupon(id.toLong, code)
          case _ => throw new DeserializationException("Improperly formed Task object")
        }
      }
    }
}



object Coupons extends Table[Coupon]("coupon") {


  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def code = column[String]("code")

  def * = id ~ code <> (Coupon, Coupon.unapply _)

  val findById = for {
    id <- Parameters[Long]
    c <- this if c.id is id
  } yield c
}