package com.winbits.api.coupon.utils

import com.typesafe.config.ConfigFactory

/**
 * Created by manuel on 3/26/14.
 */
object Configs {
  val c = ConfigFactory.load()

  val dbHost       = c.getString("db.host")
  val dbPort       = c.getInt("db.port")
  val dbName     = c.getString("db.name")
  val dbUser     = c.getString("db.user")
  val dbPassword     = c.getString("db.password")


}
