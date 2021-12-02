package controler

import cats.effect.IO
import model.World
import model.World.{ worldStteTotal}
import model.common.Environment
import java.util.logging.{Level}


object SimulationEngine {


  def setup(params:Environment):IO[Unit] ={
      println("setup1")
    for{
      environement <- IO pure {Environment(params.temperature,params.plant  ,params.buttefly ,params.predator , params.days)}
      model  <- mainLoop(World(environement))
      _ <-  IO pure {println("test setup input"+model)}
    }yield model
  }

  def mainLoop(world: World): IO[World] = for {

    //a <- updateState(world)
    x <- processInput
    c <- updateGame(world)
   //d <- IO {println("mainloop")}
    d <- render(c)

  } yield{
  println("mainLoop")
      c

  }


   def processInput(): IO[Unit] = IO pure {
    print(Level.INFO, "..process input..")
  }

   def updateGame(world: World):  IO [World]=  IO pure {
    println("..update game: elapsed " )
     //updateState(world)
     //checkCollision(world)
     worldStteTotal(world)
  }
  def render (newWorld : World): IO [Unit] = IO pure {
    println(Level.INFO, "....rendered")
  }



}
