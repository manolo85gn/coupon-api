package com.winbits.api.coupon.domain

import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by manuel on 3/26/14.
 */
case class Coupon(id: Long,
                  code: String)

object Coupons extends Table[Coupon]("coupon") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def code = column[String]("code")

  def * = id ~ code <>(Coupon, Coupon.unapply _)

  val findById = for {
    id <- Parameters[Long]
    c <- this if c.id is id
  } yield c
}

