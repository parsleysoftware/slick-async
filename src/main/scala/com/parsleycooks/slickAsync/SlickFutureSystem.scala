package com.parsleycooks.slickAsync

import slick.basic.{BasicProfile, DatabaseConfig}

import scala.async.internal.FutureSystem
import scala.reflect.macros.whitebox

class SlickFutureSystem[P <: BasicProfile] extends FutureSystem {
  override type Prom[A] = this.type
  override type Fut[A] = this.type // TODO: probably DBIO
  override type ExecContext = DatabaseConfig[P] // TODO: maybe use an ActionContext? need to figure out
  override type Tryy[A] = scala.util.Try[A]

  override def mkOps(c0: whitebox.Context): Ops {
    val c: c0.type
  } = ???
}
