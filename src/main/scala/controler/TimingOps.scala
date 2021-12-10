

import cats.effect.IO
import cats.effect.IO.unit


import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{DurationLong, FiniteDuration}

object TimingOps {
  implicit val timer = IO.timer(ExecutionContext.global)


}
