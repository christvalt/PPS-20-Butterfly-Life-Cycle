import controler.SimulationEngine



/** Entry point for the application */
object Launcher extends App {

  SimulationEngine.setup().unsafeRunSync
  println("finish" )



}
