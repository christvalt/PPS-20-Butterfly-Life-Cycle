package model.common

import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.Final.DEF_EQUILATERAL_ANGLE


/**
 * The BoundingBox trait is define by a point2D in the bounding box .
 */

sealed trait BoundingBox {
  def point: Point2D
}


/**
 * Bounding Box object, creates different Bounding Box types [Circle, Rectangle ...] .
 */
object BoundingBox {

  /** case classes for the different types of Bounding Boxes*/

  /**
   * Circle bounding box is used by [creature] implementation [model.reaction.SimultaionObjectImpl.eggImpl]
   * larvaeImpl, puppaImpl,butterflyImpl .
   * @param point   of Point2D class that represent the center of the Circle.
   * @param radius of the Circle Bounding box.
   */

  case class Circle(point: Point2D, radius: Double) extends BoundingBox

  /**
   *
   * @param point
   * @param width
   * @param height
   * @return  Rectangle object
   */
  case class Rectangle(point: Point2D, width: Int, height: Int) extends BoundingBox
  /**
   *
   * @param point
   * @param height
   * @param angle
   * @return Triangle object
   */

  case class Triangle(point: Point2D, height: Int, angle: Double = DEF_EQUILATERAL_ANGLE) extends BoundingBox


  def apply(point: Point2D, radius: Int): Circle = Circle(point, radius)


  def apply(point: Point2D, width: Int, height: Int): Rectangle = Rectangle(point, width, height)

  def apply(point: Point2D, height: Int, angle: Double): Triangle = Triangle(point, height, angle)
}




object Intersection {
  /**
   * https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
   * verify if two boxes are colliding, given them.
   * param body1 the first body.
   * param body2 the second body.
   * @return true if the bodies intersect, false otherwise.
   */

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

  //center points of the two circles, and checks if distance between them are less than the two radius sum .
  /**
   *
   * @param circle1  is body1.
   * @param circle2  is body2.
   * @return true if dist(x,y)< sum(r1,r2), false otherwise.
   */


  private def circleCollideWithCircle(circle1: Circle, circle2: Circle) =
    Math.hypot(circle1.point.x - circle2.point.x, circle1.point.y - circle2.point.y) < (circle1.radius + circle2.radius)


  //takes triangle as a circle, calculating the radius of circle circumscribed in the triangle
  /**
   *
   * @param circle  is body1.
   * @param triangle is body2.
   * @return true if dist(x,y)< sum(r1,r2), false otherwise.
   */

  private def circleCollideWithTriangle(circle: Circle, triangle: Triangle) =
    Math.hypot(circle.point.x - triangle.point.x, circle.point.y - triangle.point.y) < (circle.radius + triangle.height / 3 * 2)

  private def circleCollideWithRectangle(circle: Circle, rectangle: Rectangle) = {

    rectangle.point.x + rectangle.width > circle.point.x &&
      rectangle.point.y + rectangle.height > circle.point.y &&
      circle.point.x + circle.radius > rectangle.point.x &&
      circle.point.y + circle.radius > rectangle.point.y
  }

  //these collisions are not interesting for our implementation
  /**
   *
   * @param rectangle1 is body1.
   * @param rectangle2 is body2.
   * @return
   */

  private def rectangleIntersectsRectangle(rectangle1: Rectangle, rectangle2: Rectangle) = false

  /**
   *
   * @param rectangle is body1.
   * @param triangle is body2.
   * @return
   */

  private def rectangleIntersectsTriangle(rectangle: Rectangle, triangle: Triangle) = false

  /**
   *
   * @param triangle1 is body1.
   * @param triangle2 is body2.
   * @return
   */

  private def triangleIntersectsTriangle(triangle1: Triangle, triangle2: Triangle) = false

}

