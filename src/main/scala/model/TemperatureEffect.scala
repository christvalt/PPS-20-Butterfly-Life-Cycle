package model

import model.World.timeOfTheDay
import model.creature.CreatureObject.TypeUtilities.Velocity
import utils.TrigonometricalOps.Sinusoidal.Curried.zeroPhasedZeroYTranslatedSinusoidal

object TemperatureEffect {
  val VELOCITY_MODIFIER =0.0253125f


  def standardTemperatureEffect: ((Int, Int)) => Velocity = {
    case (temperature: Int, currentIteration: Int) =>
      zeroPhasedZeroYTranslatedSinusoidal(VELOCITY_MODIFIER * temperature)(timeOfTheDay(currentIteration))
  }


}
