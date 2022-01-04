package controler

import cats.data.StateT
import cats.effect.IO
import cats.effect.IO.unit
import controler.TupleUtils.toTuple
import model.World

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{DurationLong, FiniteDuration}

object TimingOps {


  type SimulationIO[A] = IO[A] //could be not generic: type SimulationIO = IO[Unit]
  type Simulation[A] = StateT[SimulationIO, World, A] //type Simulation = StateT[SimulationIO, World, Unit]

  //helper to create StateT monad from a IO monad
  def liftIo[A](v: SimulationIO[A]): Simulation[A] = StateT[SimulationIO, World, A](s => v.map((s, _)))


  def toStateT[A](f: World => (World, A)): Simulation[A] = StateT[IO, World, A](s => IO(f(s)))


  def toStateTWorld(f: World => World): Simulation[World] = toStateT[World](w => toTuple(f(w)))


  implicit val timer = IO.timer(ExecutionContext.global)

  def getTime() = liftIo(IO { System.currentTimeMillis().millis })
  def waitUntil(from: FiniteDuration, period: FiniteDuration) =
    liftIo(if (from < period) {
      IO.sleep(period - from)
    } else unit)
}

object TupleUtils {
  def toTuple[A](a: A) = (a, a)
}
