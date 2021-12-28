package model

import model.World.timeOfTheDay
import utils.TrigonometricalOps.Sinusoidal.Curried.zeroPhasedZeroYTranslatedSinusoidal
import utils.TypeUtilities.Velocity

object TemperatureEffect {
  val VELOCITY_MODIFIER =0.0253125f


  def standardTemperatureEffect: ((Int, Int)) => Velocity = {
    case (temperature: Int, currentIteration: Int) =>
      zeroPhasedZeroYTranslatedSinusoidal(VELOCITY_MODIFIER * temperature)(timeOfTheDay(currentIteration))
  }


}
