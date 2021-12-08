package evo_sim.core

import cats.effect.IO
import cats.effect.IO.unit
import evo_sim.core.Simulation.liftIo

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{DurationLong, FiniteDuration}

object TimingOps {
  implicit val timer = IO.timer(ExecutionContext.global)

  def getTime() = liftIo(IO { System.currentTimeMillis().millis })   //def getTime() = liftIo(IO( (w: World) => (w, System.currentTimeMillis().millis)) ) //as a statet monad returns a identical new world but x seconds older

  def waitUntil(from: FiniteDuration, period: FiniteDuration) =
    liftIo(if (from < period) {
      IO.sleep(period - from)
    } else unit)
}
