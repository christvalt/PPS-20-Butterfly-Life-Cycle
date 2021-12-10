package reviseGui.viewFrame

/**import scala.swing.{BoxPanel, Button, Dimension, Label, MainFrame, Orientation, ToggleButton}


import reviseGui.SIMULATION_SIZE
import reviseGui.scene.{ControlPanel, LabelPanel, MyrmidonsPanel}

import scala.swing.{BorderPanel, Dimension, MainFrame}

/** Frame that will contains labels, control and panel where system entities will be draw.+**/

trait SimulationFrame extends MainFrame {

  def antSizeInput: Int

  def anthillFoodInput: Int

  def foodSizeInput: Int

  def obstacleSizeInput: Int

  def enemiesSizeInput: Int
}

object SimulationFrame {

  def apply(antSizeInput: Int, anthillFoodInput: Int,
            foodSizeInput: Int, obstacleSizeInput: Int,
            enemiesSizeInput: Int): SimulationFrame = new SimulationFrameImpl(antSizeInput, anthillFoodInput,
      foodSizeInput, obstacleSizeInput, enemiesSizeInput)

  /** Frame that contains labels, and panel where system entities will be draw and react.
   *
   *
   * @param antSizeInput      Parameters for ant size.
   * @param anthillFoodInput  Parameters for anthill food  size.
   * @param foodSizeInput     Parameters for food size.
   * @param obstacleSizeInput Parameters for obstacle size.
   * @param enemiesSizeInput  Parameters for enemies size
   */
  private[view] class SimulationFrameImpl(override val antSizeInput: Int,
                                          override val anthillFoodInput: Int,
                                          override val foodSizeInput: Int,
                                          override val obstacleSizeInput: Int,
                                          override val enemiesSizeInput: Int)
    extends SimulationFrame {
    title = "Myrmidons - Ant Simulator"
    val SimulationPanel: SimulationPanel = SimulationPanel()
    val controlPane: ControlPanel = ControlPanel(myrmidonsPanel)
    val labelPane: LabelPanel = LabelPanel()
    controlPane.setParameters(antSizeInput, anthillFoodInput, foodSizeInput,
      obstacleSizeInput, enemiesSizeInput)

    contents = new BorderPanel {
      layout += controlPane -> BorderPanel.Position.North
      layout += myrmidonsPanel -> BorderPanel.Position.Center
      layout += labelPane -> BorderPanel.Position.South
    }
    size = new Dimension(SIMULATION_SIZE.width, SIMULATION_SIZE.height)
    resizable = false
   }
  }
/**
class SimulationFrame extends MainFrame {
  title = "GUI Program #1"
  preferredSize = new Dimension(600, 600)
  contents = new Label("Here is the contents!")
  contents = new BoxPanel(Orientation.Horizontal) {

    contents += Button("Start") { println("Thank you") }
    contents += Button("Restart") { println("Thank you") }
    contents += Button("Stop") {  }
  }
}



object GuiProgramOne {
  def main(args: Array[String]) {
    val ui = new SimulationFrame
    ui.visible = true
    println("End of main function")
  }
}
 **/
 * **/
