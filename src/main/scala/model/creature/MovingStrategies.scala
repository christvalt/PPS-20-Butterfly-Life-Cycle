package model.creature

import model.World
import model.common.Point2D
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.{Creature, Intelligent}

import scala.math.hypot

import scala.math._




case class Movement(point: Point2D, direction: Direction)
case class Direction(angle: Int, stepToNextDirection: Int)

object MovingStrategies {
  private val random = new java.util.Random()

  def baseMovement(creature: Intelligent, world: World): Movement = {

    val chasedEntity = world.creature - creature.asInstanceOf[SimulableEntity] match {
      case set if set.nonEmpty => Option(set.minBy(distanceBetweenEntities(creature, _)))
      case _ => None
    }

    chasedEntity match {
      //case Some(chasedEntity) if distanceBetweenEntities(entity, chasedEntity) < entity.fieldOfViewRadius => chaseMovement(entity, chasedEntity)
      case _ => standardMovement(creature, creature.direction.angle, world)
    }

  }


  private def distanceBetweenEntities(a: Intelligent, b: SimulableEntity): Double = {
    sqrt(pow(b.boundingBox.point.x - a.boundingBox.point.x, 2) + pow(b.boundingBox.point.y - a.boundingBox.point.y, 2))
  }

  @scala.annotation.tailrec
  private def standardMovement(creature: Intelligent, angle: Int, world: World): Movement = {
    val direction = creature.direction.stepToNextDirection match {
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
