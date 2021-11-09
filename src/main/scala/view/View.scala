package view


object View {


//
  def settingsView:Option[SimulationSettings]=SettingsView.createAndShow
//def settingsView:IO[JPanel]=inputviewSwing.inputView(Promise[SimulationSettings])
}
//test view