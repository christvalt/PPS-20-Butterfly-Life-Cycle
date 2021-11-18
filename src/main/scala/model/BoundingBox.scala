package model

import model.BoundingBox.{Circle, Rectangle, Triangle}
import model.Point2D.DEF_EQUILATERAL_ANGLE


case class Point2D(x: Int, y: Int)



object Point2D{

  val WORLD_WIDTH = 1280

  val WORLD_HEIGHT = 720
  val DEF_EQUILATERAL_ANGLE= 20

  /**
   * @return a new Point2D in a random position within the World boundary.
   */
  def randomPosition(): Point2D = Point2D(new scala.util.Random().nextInt(WORLD_WIDTH.+(1)),
    new scala.util.Random().nextInt(WORLD_HEIGHT.+(1)))
}



sealed trait BoundingBox {
  def point: Point2D
}
object BoundingBox {


  case class Circle(point: Point2D, radius: Int) extends BoundingBox

  case class Rectangle(point: Point2D, width: Int, height: Int) extends BoundingBox


  case class Triangle(point: Point2D, height: Int, angle: Double = DEF_EQUILATERAL_ANGLE) extends BoundingBox

  def apply(point: Point2D, radius: Int): Circle = Circle(point, radius)


  def apply(point: Point2D, width: Int, height: Int): Rectangle = Rectangle(point, width, height)

  def apply(point: Point2D, height: Int, angle: Double): Triangle = Triangle(point, height, angle)
}



object Intersection {

  //Intersection between entities (Circle, Rect, Triangle)
  def intersected(body1: BoundingBox, body2: BoundingBox): Boolean = (body1, body2) match {
    case (body1: Circle, circle: Circle) => circleIntersectsCircle(body1, circle)
    case (body1: Circle, rectangle: Rectangle) => circleIntersectsRectangle(body1, rectangle)
    case (body1: Circle, triangle: Triangle) => circleIntersectsTriangle(body1, triangle)
    case (body1: Rectangle, circle: Circle) => circleIntersectsRectangle(circle, body1)
    case (body1: Rectangle, rectangle: Rectangle) => rectangleIntersectsRectangle(body1, rectangle)
    case (body1: Rectangle, triangle: Triangle) => rectangleIntersectsTriangle(body1, triangle)
    case (body1: Triangle, circle: Circle) => circleIntersectsTriangle(circle, body1)
    case (body1: Triangle, rectangle: Rectangle) => rectangleIntersectsTriangle(rectangle, body1)
    case (body1: Triangle, triangle: Triangle) => triangleIntersectsTriangle(body1, triangle)
    case _ => false
  }


  private def circleIntersectsCircle(circle1: Circle, circle2: Circle) =
    Math.hypot(circle1.point.x - circle2.point.x, circle1.point.y - circle2.point.y) < (circle1.radius + circle2.radius)

  private def circleIntersectsTriangle(circle: Circle, triangle: Triangle) =
    Math.hypot(circle.point.x - triangle.point.x, circle.point.y - triangle.point.y) < (circle.radius + triangle.height / 3 * 2)


  private def circleIntersectsRectangle(circle: Circle, rectangle: Rectangle) = {

    rectangle.point.x + rectangle.width > circle.point.x &&
      rectangle.point.y + rectangle.height > circle.point.y &&
      circle.point.x + circle.radius > rectangle.point.x &&
      circle.point.y + circle.radius > rectangle.point.y
  }



  // Collision between two rectangles (https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection#:~:text=One%20of%20the%20simpler%20forms,a%20collision%20does%20not%20exist.)

  private def rectangleIntersectsRectangle(rectangle1: Rectangle, rectangle2: Rectangle) = false

  private def rectangleIntersectsTriangle(rectangle: Rectangle, triangle: Triangle) = false

  private def triangleIntersectsTriangle(triangle1: Triangle, triangle2: Triangle) = false


}

