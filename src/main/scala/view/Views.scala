package view

import cats.effect.IO

trait Views {

  def inputReadFromUser(): IO[SimulationSettings]
  def simulationresult():Int
  def statisticRisult():Int
  def createAndShow():Option[SimulationSettings]
}
