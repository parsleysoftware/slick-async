package com.parsleycooks.slickAsync

import slick.basic.{BasicProfile, DatabaseConfig}

import scala.async.internal.FutureSystem
import scala.reflect.macros.whitebox

class SlickFutureSystem[P <: BasicProfile] extends FutureSystem {
  override type Prom[A] = this.type
  override type Fut[A] = this.type // TODO: probably DBIO
  override type ExecContext = DatabaseConfig[P] // TODO: maybe use an ActionContext? need to figure out
  override type Tryy[A] = scala.util.Try[A]

  override def mkOps(c0: whitebox.Context): Ops {val c: c0.type} = new Ops {
    val c: c0.type = c0
    import c.universe._

    override def promType[A: WeakTypeTag] = weakTypeOf[Prom[A]] // TODO sub in
    override def tryType[A: WeakTypeTag]: Type = weakTypeOf[scala.util.Try[A]]
    override def execContextType: Type = weakTypeOf[DatabaseConfig[P]]

    override def createProm[A: WeakTypeTag]: Expr[Prom[A]] = reify {
      ???
    }

    override def promiseToFuture[A: WeakTypeTag](prom: Expr[Prom[A]]): Expr[Fut[A]] = reify {
      ???
    }

    override def future[A: WeakTypeTag](a: Expr[A])(execContext: Expr[ExecContext]): Expr[Fut[A]] = reify {
      ???
    }

    override def onComplete[A, U](future: Expr[Fut[A]], fun: Expr[Tryy[A] => U], execContext: Expr[ExecContext]): Expr[Unit] = reify {
      ???
    }

    override def completeProm[A](prom: Expr[Prom[A]], value: Expr[Tryy[A]]): Expr[Unit] = reify {
      ???
    }

    override def tryyIsFailure[A](tryy: Expr[Tryy[A]]): Expr[Boolean] = reify {
      ???
    }

    override def tryyGet[A](tryy: Expr[Tryy[A]]): Expr[A] = reify {
      ???
    }

    override def tryySuccess[A: WeakTypeTag](a: Expr[A]): Expr[Tryy[A]] = reify {
      ???
    }

    override def tryyFailure[A: WeakTypeTag](a: Expr[Throwable]): Expr[Tryy[A]] = reify {
      ???
    }
  }
}
