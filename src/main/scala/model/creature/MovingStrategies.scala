package model.creature

import model.World
import model.common.Point2D
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.{Creature, Intelligent}

import scala.math.hypot

case class Movement(point: Point2D, direction: Direction)
case class Direction(angle: Int, stepToNextDirection: Int)

/** Contains utilities used to update the position of [[Intelligent]] entity. */
object MovingStrategies {


  def baseMovement(entity: Intelligent, world: World, entitiesFilter: Creature => Boolean): Movement = {
    val chasedEntity = (world.creature - entity.asInstanceOf[SimulableEntity]).filter(entitiesFilter) match {
      case set if set.nonEmpty => Option(set.minBy(elem => distanceBetweenEntities(entity.boundingBox.point, elem.boundingBox.point)))
      case _ => None
    }

    chasedEntity match {
      case Some(chasedEntity) if distanceBetweenEntities(entity.boundingBox.point, chasedEntity.boundingBox.point) < entity.fieldOfViewRadius => ???
    }
  }


  private def distanceBetweenEntities(pointA: Point2D, pointB: Point2D): Double = {
    hypot(pointB.x - pointA.x, pointB.y - pointA.y)
  }

}
