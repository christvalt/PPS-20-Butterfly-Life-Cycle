package model.common

case class Point2D(x: Int ,y: Int)

object Point2D{


  val WORLD_WIDTH = 1280

  val WORLD_HEIGHT = 720
  val DEF_EQUILATERAL_ANGLE= 20

  def randomPosition(): Point2D = Point2D(new scala.util.Random().nextInt(WORLD_WIDTH.+(1)),
    new scala.util.Random().nextInt(WORLD_HEIGHT.+(1)))
}
