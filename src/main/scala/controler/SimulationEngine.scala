package controler

import cats.effect.IO
import model.World
import model.World.worldStteTotal
import model.common.Environment
import view.SettingsView.{createAndShow, simulationResult}

import java.util.logging.Level


object SimulationEngine {


  def setup():IO[Unit] ={
    for{
      params <- IO pure {createAndShow}
      model  <- mainLoop(World(params))
    }yield model
  }

  def mainLoop(world: World): IO[World] = for {

    //a <- updateState(world)
    x <- processInput
    c <- updateGame(world)
    d <- render(c)

  } yield{
      c

  }


   def processInput(): IO[Unit] = IO pure {
    print(Level.INFO, "..get input from user..")
     createAndShow
  }

   def updateGame(world: World):  IO [World]=  IO pure {
    println("..update game2: elapsed " )
     //updateState(world)
     //checkCollision(world)
     worldStteTotal(world)
  }
  def render (newWorld : World): IO [Unit] = IO pure {
    println(Level.INFO, "....rendered")
    simulationResult(newWorld)
  }



}
