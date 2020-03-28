package com.parsleycooks.slickAsync

import slick.basic.{BasicProfile, DatabaseConfig}
import slick.dbio.DBIO

import scala.async.internal.FutureSystem
import scala.concurrent.Promise
import scala.reflect.macros.whitebox
import scala.util.{Failure, Success}

class SlickFutureSystem[P <: BasicProfile] extends FutureSystem {
  override type Prom[A] = Promise[A]
  override type Fut[A] = DBIO[A] // TODO: probably DBIO
  override type ExecContext = DatabaseConfig[P] // TODO: maybe use an ActionContext? need to figure out
  override type Tryy[A] = scala.util.Try[A]

  override def mkOps(c0: whitebox.Context): Ops {val c: c0.type} = new Ops {
    val c: c0.type = c0
    import c.universe._

    override def promType[A: WeakTypeTag] = weakTypeOf[Promise[A]]
    override def tryType[A: WeakTypeTag]: Type = weakTypeOf[scala.util.Try[A]]
    override def execContextType: Type = weakTypeOf[DatabaseConfig[P]]

    override def createProm[A: WeakTypeTag]: Expr[Prom[A]] = reify {
      Promise[A]()
    }

    override def promiseToFuture[A: WeakTypeTag](prom: Expr[Prom[A]]): Expr[Fut[A]] = reify {
      DBIO.from(prom.splice.future)
    }

    override def future[A: WeakTypeTag](a: Expr[A])(execContext: Expr[ExecContext]): Expr[Fut[A]] = reify {
      DBIO.successful(a.splice)
    }

    override def onComplete[A, U](future: Expr[Fut[A]], fun: Expr[Tryy[A] => U], execContext: Expr[ExecContext]): Expr[Unit] = reify {
      ???
    }

    override def completeProm[A](prom: Expr[Prom[A]], value: Expr[Tryy[A]]): Expr[Unit] = reify {
      prom.splice.complete(value.splice)
      c.Expr[Unit](Literal(Constant(()))).splice
    }

    override def tryyIsFailure[A](tryy: Expr[Tryy[A]]): Expr[Boolean] = reify {
      tryy.splice.isFailure
    }

    override def tryyGet[A](tryy: Expr[Tryy[A]]): Expr[A] = reify {
      tryy.splice.get
    }

    override def tryySuccess[A: WeakTypeTag](a: Expr[A]): Expr[Tryy[A]] = reify {
      Success(a.splice)
    }

    override def tryyFailure[A: WeakTypeTag](a: Expr[Throwable]): Expr[Tryy[A]] = reify {
      Failure(a.splice)
    }
  }
}
