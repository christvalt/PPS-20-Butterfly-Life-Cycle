package view

import cats.effect.IO

trait Views {
  //ststus

  def inputReadFromUser(): IO[SimulationSettings]
  def simulationresult():Int
  def statisticRisult():Int
  def createAndShow():Option[SimulationSettings]
}
