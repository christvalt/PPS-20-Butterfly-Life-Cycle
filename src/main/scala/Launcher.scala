import controler.SimulationEngine
import model.common.Environment

object Launcher extends App {

  SimulationEngine.setup().unsafeRunSync
  println("finish" )



}
