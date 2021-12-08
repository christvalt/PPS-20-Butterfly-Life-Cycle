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

  def baseMovement(entity: Intelligent, world: World): Movement = {

    val chasedEntity = world.creature - entity.asInstanceOf[SimulableEntity] match {
      case set if set.nonEmpty => Option(set.minBy(distanceBetweenEntities(entity, _)))
      case _ => None
    }

    chasedEntity match {
      //case Some(chasedEntity) if distanceBetweenEntities(entity, chasedEntity) < entity.fieldOfViewRadius => chaseMovement(entity, chasedEntity)
      case _ => standardMovement(entity, entity.direction.angle, world)
    }

  }

  //def crazyMovement(entity: Intelligent, entities: Set[Intelligent]): Intelligent = ???

  private def distanceBetweenEntities(a: Intelligent, b: SimulableEntity): Double = {
    sqrt(pow(b.boundingBox.point.x - a.boundingBox.point.x, 2) + pow(b.boundingBox.point.y - a.boundingBox.point.y, 2))
  }

  @scala.annotation.tailrec
  private def standardMovement(entity: Intelligent, angle: Int, world: World): Movement = {
    val direction = entity.direction.stepToNextDirection match {
      case 0 => Direction(random.nextInt(360), random.nextInt(50))
      case x => Direction(angle, x-1)
    }

    val deltaX = /*dt * */ entity.velocity * cos(toRadians(direction.angle)) * 0.05
    val deltaY = /*dt * */ entity.velocity * sin(toRadians(direction.angle)) * 0.05
    val x = (entity.boundingBox.point.x + deltaX).toFloat.round
    val y = (entity.boundingBox.point.y + deltaY).toFloat.round

    isBoundaryCollision(Point2D(x, y), Point2D(world.width, world.height)) match {
      case true => standardMovement(entity, random.nextInt(360), world)
      case false => Movement(Point2D(x, y), direction)
    }
  }

  /*@scala.annotation.tailrec
  private def chaseMovement(entity: Intelligent, chasedEntity: SimulableEntity): Point2D = {
    val angle = toDegrees(atan2(chasedEntity.boundingBox.point.y - entity.boundingBox.point.y, chasedEntity.boundingBox.point.x - entity.boundingBox.point.x))
    val deltaX = /*dt * */ entity.velocity * cos(toRadians(angle))
    val deltaY = /*dt * */ entity.velocity * sin(toRadians(angle))
    val x = entity.boundingBox.point.x + deltaX
    val y = entity.boundingBox.point.y + deltaY
    if (isBoundaryCollision(x, y)) chaseMovement(entity, chasedEntity) else Point2D(x.toInt, y.toInt)
  }*/

  //TODO: Bisogna considerare anche il raggio di grandezza del blob
  private def isBoundaryCollision(entityPosition: Point2D, worldDimension: Point2D): Boolean = (entityPosition.x, entityPosition.y) match {
    case (x, y) if (0 until worldDimension.x contains x) && (0 until worldDimension.y contains y) => false
    case _ => true
  }


}
