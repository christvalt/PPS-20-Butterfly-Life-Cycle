package model.common

import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.Point2D.DEF_EQUILATERAL_ANGLE



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

  def isCollidingWith(body1: BoundingBox, body2: BoundingBox): Boolean = (body1, body2) match {
    case (body1: Circle, circle: Circle) => circleCollideWithCircle(body1, circle)
    case (body1: Circle, rectangle: Rectangle) => circleCollideWithRectangle(body1, rectangle)
    case (body1: Circle, triangle: Triangle) => circleCollideWithTriangle(body1, triangle)
    case (body1: Rectangle, circle: Circle) => circleCollideWithRectangle(circle, body1)
    case (body1: Rectangle, rectangle: Rectangle) => rectangleIntersectsRectangle(body1, rectangle)
    case (body1: Rectangle, triangle: Triangle) => rectangleIntersectsTriangle(body1, triangle)
    case (body1: Triangle, circle: Circle) => circleCollideWithTriangle(circle, body1)
    case (body1: Triangle, rectangle: Rectangle) => rectangleIntersectsTriangle(rectangle, body1)
    case (body1: Triangle, triangle: Triangle) => triangleIntersectsTriangle(body1, triangle)
    case _ => false
  }


  private def circleCollideWithCircle(circle1: Circle, circle2: Circle) =
    Math.hypot(circle1.point.x - circle2.point.x, circle1.point.y - circle2.point.y) < (circle1.radius + circle2.radius)

  private def circleCollideWithTriangle(circle: Circle, triangle: Triangle) =
    Math.hypot(circle.point.x - triangle.point.x, circle.point.y - triangle.point.y) < (circle.radius + triangle.height / 3 * 2)


  private def circleCollideWithRectangle(circle: Circle, rectangle: Rectangle) = {

    rectangle.point.x + rectangle.width > circle.point.x &&
      rectangle.point.y + rectangle.height > circle.point.y &&
      circle.point.x + circle.radius > rectangle.point.x &&
      circle.point.y + circle.radius > rectangle.point.y
  }

  private def rectangleIntersectsRectangle(rectangle1: Rectangle, rectangle2: Rectangle) = false

  private def rectangleIntersectsTriangle(rectangle: Rectangle, triangle: Triangle) = false

  private def triangleIntersectsTriangle(triangle1: Triangle, triangle2: Triangle) = false


}

