package reviseGui.viewFrame

import scala.swing.MainFrame
import reviseGui.scene.ParametersPanel

import scala.swing.{BorderPanel, Dimension}

trait SettingParameterFrame extends MainFrame {

  object SettingParameterFrame {

    def apply(): SettingParameterFrame = new SettingparameterFrameImpl()
  }
}
    class SettingparameterFrameImpl() extends SettingParameterFrame {
      title = "ButterflyLCSim-Set Simulation Parameters"
      val settingPanel: ParametersPanel = ParametersPanel(this)
      contents = new BorderPanel {
        layout += settingPanel -> BorderPanel.Position.Center
      }

      size = new Dimension(400, 400)
      resizable = false
      visible = true
      centerOnScreen()
}
