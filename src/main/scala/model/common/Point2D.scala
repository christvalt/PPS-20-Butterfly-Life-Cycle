package model.common

import model.common.Final.{WORLD_HEIGHT, WORLD_WIDTH}

case class Point2D(x: Int ,y: Int)

object Point2D {





// TODO: bisogna considerare per tutti i boundibox
  def randomPosition(): Point2D = Point2D(new scala.util.Random().nextInt(WORLD_WIDTH.+(1)),
    new scala.util.Random().nextInt(WORLD_HEIGHT.+(1)))


}
