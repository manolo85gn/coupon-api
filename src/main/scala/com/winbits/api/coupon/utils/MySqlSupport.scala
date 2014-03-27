package com.winbits.api.coupon.utils

import scala.slick.driver.MySQLDriver.simple._
import com.winbits.api.coupon.utils.{ Configs => C }
/**
 * Created by manuel on 3/26/14.
 */
trait MySqlSupport {
  def db =  Database.forURL(url = "jdbc:mysql://%s:%d/%s".format(C.dbHost, C.dbPort, C.dbName),
    user = C.dbUser, password = C.dbPassword, driver = "com.mysql.jdbc.Driver")

  implicit val session: Session = db.createSession()
}
