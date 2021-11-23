package controler

import model.World
import model.World.{updateState, updateStateOfWorldParameter}

import java.util
import java.util.logging.Logger.getLogger
import java.util.logging.{Level, Logger}

object SimulationEngine {





  def setup():Unit = {
    World

  }


  def mainLoop(): Unit = {
    var previousCycleStartTime = System.currentTimeMillis
    while ( {
      true
    }) {
      val currentCycleStartTime = System.currentTimeMillis
      val elapsed = currentCycleStartTime - previousCycleStartTime
      val world = World()

      processInput()
      updateGame(world)
      render()
      waitForNextFrame(currentCycleStartTime)
      previousCycleStartTime = currentCycleStartTime
    }
  }

   val logger :Logger =getLogger("GameEngine")
   val period:Int = 1000


  def waitForNextFrame(cycleStartTime: Long): Unit = {
    val dt = System.currentTimeMillis - cycleStartTime
    if (dt < period) try Thread.sleep(period - dt)
    catch {
      case ex: Exception =>

    }
  }


   def processInput(): Unit = {
    logger.log(Level.INFO, "..process input..")
  }

   def updateGame(world: World): World= {
    logger.log(Level.INFO, "..update game: elapsed " )
        updateState(world)
  }
  def render ():Unit = {
    logger.log(Level.INFO, "....rendered")
  }



}
