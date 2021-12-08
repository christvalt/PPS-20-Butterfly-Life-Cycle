package view

import cats.effect.IO
import model.World
import model.common.Environment

import javax.swing.JPanel

trait Views extends JPanel {

  def createAndShow:Unit
  def simulationViewCrateAndShowed() : Unit
  def rendered(world : World) : Unit
}
