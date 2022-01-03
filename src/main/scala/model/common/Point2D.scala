package model.common

import model.common.Final.{WORLD_HEIGHT, WORLD_WIDTH}

/**
 * The Point2D class defines a point represents as a location (x,y) in coordinate space.
 *
 * @param x first coordinate
 * @param y second coordinate
 */
case class Point2D(x: Int ,y: Int)

object Point2D {

// TODO: bisogna considerare per tutti i boundingbox
  /**
   * @return a new Point2D in a random position in the World [model.reaction.World].
   */
  def randomPosition(): Point2D = Point2D(new scala.util.Random().nextInt(WORLD_WIDTH.+(1)),
    new scala.util.Random().nextInt(WORLD_HEIGHT.+(1)))


}
