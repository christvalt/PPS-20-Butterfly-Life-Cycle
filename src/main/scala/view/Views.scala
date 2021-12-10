package view

import model.World

import javax.swing.JPanel

trait Views extends JPanel {

  def createAndShow:Unit
  def simulationViewCrateAndShowed() : Unit
  def rendered(world : World) : Unit
}
