package controler

import cats.effect.IO
import controler.TimingOps.{Simulation, getTime, liftIo, toStateTWorld, waitUntil}

import scala.language.postfixOps
import scala.concurrent.duration._
import model.World
import model.World.{checkCollisionToState, wordUpdateToState}
import view.SettingsView
import view.SettingsView.{createAndShow, simulationViewCrateAndShowed}

import java.util.logging.Level


/** The engine of the simulation, responsible for setting up the simulation, updating at a constant rate
 * detecting and handle collisions, displaying the World updated
 */

object SimulationEngine {

  /**
   *  Provides a monadic description of the actions required to start the simulation.
   *  The simulation does not start until invoking "run" on the [[IO]] returned from this function.
   */
  def setup():IO[Unit] ={
    for{
       _ <- IO {
         log("building gui")
         processInput()
       }
       env <- inputReadFromUser()
       _ <- IO {
         log("calling simulation  loop")
         (for{
           _ <- IO {simulationViewCrateAndShowed()}
           _ <- mainLoop().runS(World(env))
         }yield ()).unsafeRunSync()

       }
    }yield ()
  }

  /** Provides a lazy description of the cyclic behaviour of the simulation as a sequence of unit and flatmap
   * applications.
   * Every simulation iteration is made up of ad update step, followed by a collisions-detection-and-handling phase
   * and by a display step.
   * World is updated at a constant rate
   * This method doesn't actually run the simulation until performing "run" on the [[IO]] returned.
   */
  def mainLoop(): Simulation[Unit] = for {
    _ <- toStateTWorld { (w: World) => {
      log("it " + w.currentIteration + " / " + w.totalIterations)
      w
    }}
    startTime <- getTime
    b <- wordUpdateToState
    c <- checkCollisionToState
    d <- render(c)
    currentTime <- getTime
    e <- waitUntil(currentTime - startTime, 10 millis)
    f <- if (c.currentIteration < c.totalIterations)
      mainLoop()else
      liftIo( for {
        e1 <- IO {log("End of simulation..... print stat")  }
      } yield ())

  } yield()


  /**simple get input from user call method with monad to avoid sideEffect*/
  def inputReadFromUser() =
    IO {
      SettingsView.getInputUser()
    }

  /**simple view creation of view call method with monad to avoid sideEffect*/

   def processInput(): IO[Unit] = IO pure {
    print(Level.INFO, "..get input from user..")
     createAndShow
  }

   /**risult rendered during the simulation */
  def render (newWorld : World)=
    liftIo(IO {
      SettingsView.rendered(newWorld)
    })





  def log(message: String) =  IO pure  {println(Thread.currentThread.getName+": " + message)}

}


