package com.winbits.api.coupon.dao

import com.winbits.api.coupon.utils.MySqlSupport
import com.winbits.api.coupon.domain._
import java.sql._


/**
 * Created by manuel on 3/26/14.
 */
object CouponDAO extends  MySqlSupport {

  def get(id: Long): Either[Failure, Coupon] = {
    try {

      db.withSession {
        Coupons.findById(id).firstOption match {
          case Some(coupon: Coupon) =>
            Right(coupon)
          case _ =>
            Left(notFoundError(id))
        }
      }

    } catch {
      case e: SQLException =>
        Left(databaseError(e))
    }
  }

  protected def databaseError(e: SQLException) =
    Failure("%d: %s".format(e.getErrorCode, e.getMessage), FailureType.DatabaseFailure)

  protected def notFoundError(couponId: Long) =
    Failure("Coupon with id=%d does not exist".format(couponId), FailureType.NotFound)

}
