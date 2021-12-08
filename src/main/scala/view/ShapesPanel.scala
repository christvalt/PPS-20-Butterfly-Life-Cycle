package view

import model.BoundingBox.{Circle, Rectangle, Triangle}
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.{Intersection, World}
import model.common.Point2D
import model.creature.CreatureObject.{Butterfly, Creature}

import java.awt.{Color, Dimension, Graphics}
import javax.swing.JPanel


class ShapesPanel(world: World) extends JPanel {
  val MIN_TEMPERATURE: Int = -20
  val MAX_TEMPERATURE = 75
  val DEF_TEMPERATURE = 25
  val TEMPERATURE_OFFSET: Int = -MIN_TEMPERATURE

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    // draw temperature filter
    val redIntensity = modelToViewRatio(world.temperature - MIN_TEMPERATURE, 255, MAX_TEMPERATURE - MIN_TEMPERATURE)
    val blueIntensity = 255 - redIntensity
    val temperatureColor = new Color(redIntensity, 0, blueIntensity, 75)
    g.setColor(temperatureColor)
    g.fillRect(0, 0, getWidth, getHeight)

    // draw rectangles and triangles before the transparent filter and circles
    world.creature.foreach(e => drawFoodObstacleOrPlant(g, e))

    // draw luminosity filter
   /// val alpha = 255 - modelToViewRatio(world.luminosity, 255, MAX_LUMINOSITY).max(0).min(255)
    //    val luminosityColor = new Color(0, 0, 0, alpha)
    //    g.setColor(luminosityColor)
    //    g.fillRect(0, 0, getWidth, getHeight)

    // draw blob circles with the field of view
    drawBlobs(g)
  }

  override def getPreferredSize: Dimension = {
    val borderValue = 15
    new Dimension(getParent.getSize().width - borderValue, getParent.getSize().height - borderValue)
  }

  private def drawFieldOfView(g: Graphics, b: Butterfly): Unit = {
    g.setColor(new Color(255, 255, 0))
    val x = modelToViewRatio(b.boundingBox.point.x - b.fieldOfViewRadius, getWidth, world.width)
    val y = modelToViewRatio(b.boundingBox.point.y - b.fieldOfViewRadius, getHeight, world.height)
    val width = modelToViewRatio(b.fieldOfViewRadius * 2, getWidth, world.width)
    val height = modelToViewRatio(b.fieldOfViewRadius * 2, getHeight, world.height)
    g.drawOval(x, y, width, height)
    world.creature.filter(e2 => Intersection.isCollidingWith(Circle(b.boundingBox.point,
      b.fieldOfViewRadius), e2.boundingBox)).foreach(e2 => drawFoodObstacleOrPlant(g, e2))
  }


  private def drawBlobs(g: Graphics): Unit = {
    world.creature.foreach(e => {
      // draw field of view for each blob
      e match {
        case b: Butterfly => drawFieldOfView(g, b)
        case _ =>
      }
      e.boundingBox match {
        case Circle(point2D, r) =>
          e match {
            case _: EggsImpl => g.setColor(Color.BLUE)
            case _: PuppaImpl => g.setColor(Color.RED)
            case _: LarvaImpl => g.setColor(Color.DARK_GRAY)
            case _: ButterflyImpl => g.setColor(Color.MAGENTA)
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


  private def drawFoodObstacleOrPlant(g: Graphics, entity: Creature): Unit = {
    entity.boundingBox match {
      case Rectangle(point2D, w, h) =>
        entity match {
          case _: PredatorImpl => g.setColor(Color.RED)
          case _: flourPlant => g.setColor(Color.GREEN)
          case _: NectarPlant => g.setColor(Color.PINK)
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


  private def triangleVertices(tri: Triangle): (Point2D, Point2D, Point2D) = {
    val radius = tri.height / 3 * 2
    (Point2D(tri.point.x, tri.point.y + radius), Point2D(tri.point.x - radius, tri.point.y - radius),
      Point2D(tri.point.x + radius, tri.point.y - radius))
  }


  private def modelToViewRatio(modelProperty: Double, viewDimension: Double, modelDimension: Double): Int =
    (modelProperty * viewDimension / modelDimension).round.toInt

}
