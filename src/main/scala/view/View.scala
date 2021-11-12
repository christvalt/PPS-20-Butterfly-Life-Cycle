package view

import cats.effect.IO
import view.SwingView.{inputReadFromUser, inputviewSwing}
import view.SwingView.inputviewSwing.inputView

import java.awt.Component
import javax.swing.JPanel
import scala.concurrent.Promise


object View {

//testing
  //
  def settingsView:Option[SimulationSettings]=SettingsView.createAndShow
  //def settingsView:IO[JPanel]=inputviewSwing.inputView(Promise[SimulationSettings])
}
//test view