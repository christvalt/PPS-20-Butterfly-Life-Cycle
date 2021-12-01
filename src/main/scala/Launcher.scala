import cats.effect.IO
import controler.SimulationEngine
import model.common.Environment

object Launcher extends App {



  val parameters =Environment(temperature = 1,2  ,2 ,2 , 2)

  SimulationEngine.setup(parameters).unsafeRunSync
  println("working"+parameters.temperature)



}
