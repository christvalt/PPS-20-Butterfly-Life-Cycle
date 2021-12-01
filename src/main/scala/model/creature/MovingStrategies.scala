package evo_sim.model.entities.entityStructure.movement

import evo_sim.model.entities.entityBehaviour.EntityBehaviour.SimulableEntity
import evo_sim.model.entities.entityStructure.EntityStructure.{Entity, Intelligent}
import evo_sim.model.entities.entityStructure.Point2D
import evo_sim.model.world.World
import scala.math.hypot

case class Movement(point: Point2D, direction: Direction)
case class Direction(angle: Int, stepToNextDirection: Int)

/** Contains utilities used to update the position of [[Intelligent]] entity. */
object MovingStrategies {

  /** Creates a new [[Movement]] object containing the new position and direction based on the previous
   *  position and direction. The new position will approach the closest eatable entity if this is inside
   *  the FoV (Field of View), otherwise it will follow the predetermined direction. The entity will hold
   *  one direction for a random number of iterations, after which a new direction is randomly calculated.
   *
   *  @param entity to be moved.
   *  @param world containing all the simulation information.
   *  @param entitiesFilter that describes all possible eatable entities.
   *  @return a Movement that contains the new position and the new direction.
   * */
  def baseMovement(entity: Intelligent, world: World, entitiesFilter: Entity => Boolean): Movement = {
    val chasedEntity = (world.entities - entity.asInstanceOf[SimulableEntity]).filter(entitiesFilter) match {
      case set if set.nonEmpty => Option(set.minBy(elem => distanceBetweenEntities(entity.boundingBox.point, elem.boundingBox.point)))
      case _ => None
    }

    chasedEntity match {
      case Some(chasedEntity) if distanceBetweenEntities(entity.boundingBox.point, chasedEntity.boundingBox.point) < entity.fieldOfViewRadius =>
        MovingStrategiesProlog.chaseMovement(entity, chasedEntity.boundingBox.point, (world.width, world.height))
      case _ => MovingStrategiesProlog.standardMovement(entity, (world.width, world.height))
    }
  }

  /**
   * Calculates the distance between two points on the 2-Dimensional Cartesian plane.
   * @param pointA the first point.
   * @param pointB the second point.
   * @return the distance between the two points passed as a parameter.
   */
  private def distanceBetweenEntities(pointA: Point2D, pointB: Point2D): Double = {
    hypot(pointB.x - pointA.x, pointB.y - pointA.y)
  }

}
