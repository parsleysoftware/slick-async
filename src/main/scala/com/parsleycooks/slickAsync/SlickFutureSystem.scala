package com.parsleycooks.slickAsync

import slick.basic.{BasicProfile, DatabaseConfig}

import scala.async.internal.FutureSystem
import scala.reflect.macros.whitebox

class SlickFutureSystem[P <: BasicProfile] extends FutureSystem {
  override type Prom = this.type
  override type Fut = this.type
  override type ExecContext = DatabaseConfig[P] // TODO: maybe use an ActionContext? need to figure out
  override type Tryy = this.type

  override def mkOps(c0: whitebox.Context): Ops {
    val c: c0.type
  } = ???
}
