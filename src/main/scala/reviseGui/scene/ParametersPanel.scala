package reviseGui.scene

import reviseGui.viewFrame.SettingParameterFrame
import scala.swing.{Button, GridPanel, Label, TextField}





object ParametersPanel {

  def apply(frame: SettingParameterFrame): GridPanel = new ParametersPanelImpl()


  /** Panel to set simulation parameters.
   *
   * @parameterFrame Frame where this panel is display and parameters are set by user.
   */

  private[reviseGui] class ParametersPanelImpl()
    extends GridPanel(PARAMETER_GRID._1, PARAMETER_GRID._2)  {

    private val antSize = new Label("Number of Ant (F+P)")
    private val antSizeInput = new TextField()





    private val anthillFood = new Label("Number of food in anthill")
    private val anthillFoodInput = new TextField()
    private val foodSize = new Label("Number of food")
    private val foodSizeInput = new TextField()
    private val obstacleSize = new Label("Number of obstacle")
    private val obstacleSizeInput = new TextField()
    private val enemiesSize = new Label("Number of enemies")
    private val enemiesSizeInput = new TextField()
    private val setButton = new Button("Set Simulation")
    private val setDefaultButton = new Button("Set Default")

    contents ++= Seq(antSize, antSizeInput, anthillFood, anthillFoodInput, foodSize, foodSizeInput,
      obstacleSize, obstacleSizeInput, enemiesSize, enemiesSizeInput, setDefaultButton, setButton)
  }
}

    /**
     *setting parameters with text field / default parameter.
     */
    /**listenTo(setButton, setDefaultButton)

    reactions += {
      case ButtonClicked(`setButton`) =>
        LaunchSimulationWithParameters (antSizeInput ,anthillFoodInput, foodSizeInput,
          obstacleSizeInput, enemiesSizeInput)

    }**/

    /**
     * Show SimulationFrame with user parameters.
     *
     * @param antSizeInput      how many ant in simulation.
     * @param anthillFoodInput  how many anthill food  in simulation.
     * @param foodSizeInput     how many food number in simulation.
     * @param obstacleSizeInput how many obstacle number in simulation.
     * @param enemiesSizeInput  how many enemies number in simulation.
     */
 /**   private def LaunchSimulationWithParameters(antSizeInput: Int, anthillFoodInput: Int, foodSizeInput: Int,
                                             obstacleSizeInput: Int, enemiesSizeInput: Int): Unit = {
      frame.dispose()
      val simulationFrame = SimulationFrame(antSizeInput, anthillFoodInput, foodSizeInput,
        obstacleSizeInput, enemiesSizeInput)
      simulationFrame.visible = true
    }
  }**/



