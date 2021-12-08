package view

import cats.effect.IO
import model.World
import model.common.Environment

import javax.swing.JPanel

trait Views extends JPanel {
  //ststus

  def inputReadFromUser(): Environment
  def simulationresult():Int
  def statisticRisult():Int
  def createAndShow():Environment
  def simulationResult(world : World) : Unit
}
