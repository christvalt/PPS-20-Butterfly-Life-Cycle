package evo_sim.view.swing.custom.components

import java.awt.{Color, Dimension, Graphics}

import evo_sim.model.entities.entityStructure.BoundingBox.{Circle, Rectangle, Triangle}
import evo_sim.model.world.Constants._
import evo_sim.model.entities.Entities._
import evo_sim.model.entities.entityStructure.EntityStructure.{Blob, Entity, Obstacle}
import evo_sim.model.entities.entityStructure.{Intersection, Point2D}
import evo_sim.model.world.World
import javax.swing.JPanel

/**
 * JPanel implementation that visualizes world entities in a bidimensional space.
 *
 * @param world World to represent
 */
class ShapesPanel(world: World) extends JPanel {

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    // draw temperature filter
    val redIntensity = modelToViewRatio(world.temperature - MIN_TEMPERATURE, 255, MAX_TEMPERATURE - MIN_TEMPERATURE)
    val blueIntensity = 255 - redIntensity
    val temperatureColor = new Color(redIntensity, 0, blueIntensity, 75)
    g.setColor(temperatureColor)
    g.fillRect(0, 0, getWidth, getHeight)

    // draw rectangles and triangles before the transparent filter and circles
    world.entities.foreach(e => drawFoodObstacleOrPlant(g, e))

    // draw luminosity filter
    val alpha = 255 - modelToViewRatio(world.luminosity, 255, MAX_LUMINOSITY).max(0).min(255)
    val luminosityColor = new Color(0, 0, 0, alpha)
    g.setColor(luminosityColor)
    g.fillRect(0, 0, getWidth, getHeight)

    // draw blob circles with the field of view
    drawBlobs(g)
  }

  override def getPreferredSize: Dimension = {
    val borderValue = 15
    new Dimension(getParent.getSize().width - borderValue, getParent.getSize().height - borderValue)
  }

  /**
   * Draws field of view of a blob as a circle and interesected entities.
   *
   * @param g Graphics used by paintComponent method
   * @param b Blob used to represent his field of view
   * @return a set with the blob produced by the effect
   */
  private def drawFieldOfView(g: Graphics, b: Blob): Unit = {
    g.setColor(new Color(255, 255, 0))
    val x = modelToViewRatio(b.boundingBox.point.x - b.fieldOfViewRadius, getWidth, world.width)
    val y = modelToViewRatio(b.boundingBox.point.y - b.fieldOfViewRadius, getHeight, world.height)
    val width = modelToViewRatio(b.fieldOfViewRadius * 2, getWidth, world.width)
    val height = modelToViewRatio(b.fieldOfViewRadius * 2, getHeight, world.height)
    g.drawOval(x, y, width, height)
    world.entities.filter(e2 => Intersection.intersected(Circle(b.boundingBox.point,
      b.fieldOfViewRadius), e2.boundingBox)).foreach(e2 => drawFoodObstacleOrPlant(g, e2))
  }

  /**
   * Draws blobs as circles.
   *
   * @param g Graphics used by paintComponent method
   */
  private def drawBlobs(g: Graphics): Unit = {
    world.entities.foreach(e => {
      // draw field of view for each blob
      e match {
        case b: Blob => drawFieldOfView(g, b)
        case _ =>
      }
      e.boundingBox match {
        case Circle(point2D, r) =>
          e match {
            case _: BaseBlob => g.setColor(Color.BLUE)
            case _: CannibalBlob => g.setColor(Color.RED)
            case _: SlowBlob => g.setColor(Color.DARK_GRAY)
            case _: PoisonBlob => g.setColor(Color.MAGENTA)
            case _ => g.setColor(Color.BLACK)
          }
          val x = modelToViewRatio(point2D.x - r, getWidth, world.width)
          val y = modelToViewRatio(point2D.y - r, getHeight, world.height)
          val ovalWidth = modelToViewRatio(r * 2, getWidth, world.width)
          val ovalHeight = modelToViewRatio(r * 2, getHeight, world.height)
          g.fillOval(x, y, ovalWidth, ovalHeight)
        case _ =>
      }
    })
  }

  /**
   * Draws a entity with triangle or rectangle bounding box (foods, obstacles and plants).
   *
   * @param g Graphics used by paintComponent method
   * @param entity Entity to represent
   */
  private def drawFoodObstacleOrPlant(g: Graphics, entity: Entity): Unit = {
    entity.boundingBox match {
      case Rectangle(point2D, w, h) =>
        entity match {
          case _: Obstacle => g.setColor(Color.RED)
          case _: StandardPlant => g.setColor(Color.GREEN)
          case _: ReproducingPlant => g.setColor(Color.PINK)
          case _: PoisonousPlant => g.setColor(Color.MAGENTA)
          case _ => g.setColor(Color.black)
        }
        val x = modelToViewRatio(point2D.x - w / 2, getWidth, world.width)
        val y = modelToViewRatio(point2D.y - h / 2, getHeight, world.height)
        val rectWidth = modelToViewRatio(w, getWidth, world.width)
        val rectHeight = modelToViewRatio(h, getHeight, world.height)
        g.fillRect(x, y, rectWidth, rectHeight)
      case Triangle(point2D, h, a) =>
        val vertices = triangleVertices(Triangle(point2D, h, a))
        g.setColor(Color.green)
        val xPoints = vertices.productIterator.map({
          case p: Point2D =>
            modelToViewRatio(p.x, getWidth, world.width)
        }).toArray
        val yPoints = vertices.productIterator.map({
          case p: Point2D =>
            modelToViewRatio(p.y, getHeight, world.height)
        }).toArray
        val nPoints = vertices.productIterator.length
        g.fillPolygon(xPoints, yPoints, nPoints)
      case _ =>
    }
  }

  /** Returns a tuple3 with the vertices
   * v1 = center.x, center.y  + radius           -> upper vertices
   * v2 = center.x - radius, center.y  - radius  -> bottom left vertices
   * v1 = center.x + radius, center.y  - radius  -> bottom right vertices
   *
   * @param tri Triangle bounding box
   * @return triangle vertices
   */
  private def triangleVertices(tri: Triangle): (Point2D, Point2D, Point2D) = {
    val radius = tri.height / 3 * 2
    (Point2D(tri.point.x, tri.point.y + radius), Point2D(tri.point.x - radius, tri.point.y - radius),
      Point2D(tri.point.x + radius, tri.point.y - radius))
  }

  /** Proportional mapping of a model property from model to view dimensions
   *
   * @param modelProperty A model property
   * @param viewDimension Maximum view dimension
   * @param modelDimension Maximum model dimension
   * @return Equivalent value of model property in view space
   */
  private def modelToViewRatio(modelProperty: Double, viewDimension: Double, modelDimension: Double): Int =
    (modelProperty * viewDimension / modelDimension).round.toInt

}
