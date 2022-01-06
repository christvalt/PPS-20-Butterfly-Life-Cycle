package view

import model.World

import javax.swing.JPanel

/** Provides the methods of the view for  the simulation */

trait Views extends JPanel {

  /**create and get the simulatino initial parameter*/
  def createAndShow:Unit
  /**report the simulation*/
  def simulationViewCrateAndShowed() : Unit
  /**show the simulation processus*/
  def rendered(world : World) : Unit
}
