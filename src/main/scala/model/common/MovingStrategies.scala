package model.common

import model.SimulationObjectImpl.EggsImpl
import model.World
import model.creature.CreatureObject.Intelligent
import scala.math._


/**This class contain to update the position different creature in the simulation
 *
 * */

case class Movement(point: Point2D, direction: Direction)
case class Direction(angle: Int, anglePositionY: Int)




object MovingStrategies {
  private val random = new java.util.Random()


  /** Crete a new Movement object contaiming the new position and direction based on previous positionand direction
   *
   * Params:
   *entity – to be moved.
   *world – containing all the simulation information.
   *entitiesFilter – that describes all possible eatable entities.
   *Returns:a Movement that contains the new position and the new direction
   * */
  def baseMovement(creature: Intelligent, world: World): Movement = creature match{
    case creature: EggsImpl =>eggMove(creature, world)
    case _ => standardMovement(creature, creature.direction.angle, world)

  }
// TODO evaluate the position and insert small moving

  /** Represent to logic of moving just for Egg creature
   * */
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


  /**
   * Calculates the distance between two points on the 2-Dimensional Cartesian plane.
   * @param pointA the first point.
   * @param pointB the second point.
   * @return the distance between the two points passed as a parameter.
   */
  private def isBoundaryCollision(entityPosition: Point2D, worldDimension: Point2D): Boolean = (entityPosition.x, entityPosition.y) match {
    case (x, y) if (0 until worldDimension.x contains x) && (0 until worldDimension.y contains y) => false
    case _ => true
  }


}
