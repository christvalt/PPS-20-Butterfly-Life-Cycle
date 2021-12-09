package controler

import breeze.numerics.log
import cats.effect.IO
import controler.TimingOps.{Simulation, getTime, liftIo, toStateTWorld, waitUntil}

import scala.language.postfixOps
import scala.concurrent.duration._
import model.World
import model.World.{checkCollision, checkCollisionToState, wordUpdateToState}
import view.SettingsView
import view.SettingsView.{createAndShow, simulationViewCrateAndShowed}

import java.util.logging.Level


object SimulationEngine {


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
       // e2 <- IO { View.resultViewBuiltAndShowed(worldAfterCollisions) }
      } yield ())

  } yield()


  def inputReadFromUser() =
    IO {
      SettingsView.getInputUser()
    }


   def processInput(): IO[Unit] = IO pure {
    print(Level.INFO, "..get input from user..")
     createAndShow
  }

   def updateGame(world: World):  IO [World]=  IO pure {
    println("..update game2: elapsed " )
     //updateState(world)
     checkCollision(world)
     //worldStteTotal(world)
  }
  def render (newWorld : World)=
    liftIo(IO {
      SettingsView.rendered(newWorld)
    })





  def log(message: String) =  IO pure  {println(Thread.currentThread.getName+": " + message)}

}


