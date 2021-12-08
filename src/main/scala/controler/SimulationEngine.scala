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
import scala.sys.env


object SimulationEngine {


  def setup():IO[Unit] ={
    for{
       _ <- IO {
         processInput()
       }
       env <- inputReadFromUser()
       _ <- IO {
         (for{
           _ <- IO {simulationViewCrateAndShowed()}
           _ <- mainLoop().runS(World(env))
         }yield ()).unsafeRunSync()

       }
    }yield ()
  }

  def mainLoop(): Simulation[Unit] = for {
    _ <- toStateTWorld { (w: World) => {
      println("it " + w.currentIteration + " / " + w.totalIterations)
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
        e1 <- IO {  }
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






}
object Logging {
  def log(message: String) =  IO pure  {println(Thread.currentThread.getName+": " + message)}
}
