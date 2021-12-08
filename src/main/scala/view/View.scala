package view

import cats.effect.IO
import model.common.Environment
import view.SwingView.{inputReadFromUser, inputviewSwing}
import view.SwingView.inputviewSwing.inputView

import java.awt.Component
import javax.swing.JPanel
import scala.concurrent.Promise


object View {

//testing
  //
  def settingsView:Environment=SettingsView.createAndShow
  //def settingsView:IO[JPanel]=inputviewSwing.inputView(Promise[SimulationSettings])
}
//test view