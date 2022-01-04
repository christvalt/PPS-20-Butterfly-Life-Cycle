package model.common

import model.SimulationObjectImpl.EggsImpl
import model.common.Final.{WORLD_HEIGHT, WORLD_WIDTH}



/**
 * The Point2D class defines a point represents as a location (x,y) in coordinate space.
 *
 * @param x first coordinate
 * @param y second coordinate
 */

case class Point2D(x: Int ,y: Int)

object Point2D {




  /**
   * @return a new Point2D in a random position in the World [model.reaction.World].
   */
// TODO: bisogna considerare per tutti i boundibox
  def randomPosition(): Point2D = Point2D(new scala.util.Random().nextInt(WORLD_WIDTH.+(1)),
    new scala.util.Random().nextInt(WORLD_HEIGHT.+(1)))




  /**
   * @return a new Point2D in a random position in the World [model.reaction.World].
   */
  def randomPositionForEgg(): Point2D = Point2D(new scala.util.Random().nextInt(900.+(1)),
    new scala.util.Random().nextInt(400.+(1)))




  /*def combinePosition(): Point2D = {

    case e: EggsImpl => randomPositionForEgg()
    case _ => randomPosition()
  }*/
}


