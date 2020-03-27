package com.parsleycooks.slickAsync

import scala.async.internal.FutureSystem
import scala.reflect.macros.whitebox

class SlickFutureSystem extends FutureSystem {
  override type Prom = this.type
  override type Fut = this.type
  override type ExecContext = this.type
  override type Tryy = this.type

  override def mkOps(c0: whitebox.Context): Ops {
    val c: c0.type
  } = ???
}
