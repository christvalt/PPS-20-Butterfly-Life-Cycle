package reviseGui.scene

import scala.swing.{Button, GridPanel, Label, TextField}

trait ControlPanel extends GridPanel{
  /** Set simulation parameters */
  /**def setParameters(antSize: Int, anthillFood: Int, foodSize: Int,
                    obstacleSize: Int, enemiesSize: Int): Unit
  def simPanel: ButterflyLCPanel


}

object ControlPanel{
    def Apply (simPanel: ButterflyLCPanel): ControlPanel = new ControlPanelImpl(simPanel) )


  /**simulation control panel
   * @
   */

  private[reviseGui] class ControlPanelImpl( val simPanel: ButterflyLCPanel)
  private val system = ActorSystem("Myrmidons-system")
  private val boundary = Boundary(-CENTER.width, CENTER.height, SIMULATION_BOUNDARY.width, SIMULATION_BOUNDARY.height)
  private val stepLabel = new Label("Step:")
  private val populationLabel = new Label("Ants number:")
  private val anthillFoodAmountLabel = new Label("Anthill Food:")
  private val startButton = new Button("Start")
  private val stopButton = new Button("Stop")
  private val restartButton = new Button("Restart")
  private val reportButton = new Button("Report")
  private val timeLabel = new Label("Clock Rate:")
  private val timeInput = new TextField(columns = 3)
  private val buttonSetTime = new Button("Set Rate")
  private val step = new Label("0")
  private val antPopulation = new Label("0")
  private val anthillFoodAmount = new Label("0")
  private var antSize = 0
  private var anthillFood = 0
  private var obstacleSize = 0
  private var enemiesSize = 0
  private var foodSize = 0
   **/
}
