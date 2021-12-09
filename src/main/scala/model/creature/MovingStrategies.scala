package model.creature

import model.SimulationObjectImpl.EggsImpl
import model.World
import model.common.Point2D
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.{Creature, Intelligent}

import scala.math.hypot
import scala.math._




case class Movement(point: Point2D, direction: Direction)
case class Direction(angle: Int, anglePositionY: Int)

object MovingStrategies {
  private val random = new java.util.Random()

  def baseMovement(creature: Intelligent, world: World): Movement = creature match{
    case creature: EggsImpl =>eggMove(creature, world)
    case _ => standardMovement(creature, creature.direction.angle, world)

  }
// TODO evaluate the position and insert small moving
  def eggMove(creature:EggsImpl, world: World):Movement ={

    val finaAngle = creature.direction.anglePositionY match {
      case 0 => Direction(10, 7)
      case _ => Direction(10, 7)
    }
    val newXPosition =creature.velocity*cos(finaAngle.angle)*0.1
    val newYPosition =creature.velocity*cos(finaAngle.angle)*0.1
    val x =(creature.boundingBox.point.x+newXPosition).toFloat.round
    val y =(creature.boundingBox.point.x+newYPosition).toFloat.round
    isBoundaryCollision(Point2D(x, y), Point2D(world.width, world.height)) match {
      case true => standardMovement(creature, random.nextInt(360), world)
      case false => Movement(Point2D(x, y), finaAngle)
    }

  }

  @scala.annotation.tailrec
  private def standardMovement(creature: Intelligent, angle: Int, world: World): Movement = {

    val direction = creature.direction.anglePositionY match {
      case 0 => Direction(random.nextInt(360), random.nextInt(50))
      case x => Direction(angle, x-1)
    }

    val deltaX = /*dt * */ creature.velocity * cos(toRadians(direction.angle)) * 0.05
    val deltaY = /*dt * */ creature.velocity * sin(toRadians(direction.angle)) * 0.05
    val x = (creature.boundingBox.point.x + deltaX).toFloat.round
    val y = (creature.boundingBox.point.y + deltaY).toFloat.round

    isBoundaryCollision(Point2D(x, y), Point2D(world.width, world.height)) match {
      case true => standardMovement(creature, random.nextInt(360), world)
      case false => Movement(Point2D(x, y), direction)
    }
  }



  //TODO: Bisogna considerare anche il raggio di grandezza del blob
  private def isBoundaryCollision(entityPosition: Point2D, worldDimension: Point2D): Boolean = (entityPosition.x, entityPosition.y) match {
    case (x, y) if (0 until worldDimension.x contains x) && (0 until worldDimension.y contains y) => false
    case _ => true
  }


}
