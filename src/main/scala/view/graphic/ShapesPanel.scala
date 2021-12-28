package view.graphic

import model.SimulationObjectImpl._
import model.World
import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.Point2D

import java.awt.{Color, Dimension, Graphics}
import javax.swing.JPanel


class ShapesPanel(world: World) extends JPanel {
  val MIN_TEMPERATURE: Int = -20
  val MAX_TEMPERATURE = 75
  val DEF_TEMPERATURE = 25
  val TEMPERATURE_OFFSET: Int = -MIN_TEMPERATURE

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val redIntensity = modelToViewRatio(world.temperature - MIN_TEMPERATURE, 255, MAX_TEMPERATURE - MIN_TEMPERATURE)
    val blueIntensity = 255 - redIntensity
    val temperatureColor = new Color(redIntensity, 0, blueIntensity, 75)
    g.setColor(temperatureColor)

    g.fillRect(0, 0, getWidth, getHeight)

    world.creature.foreach(e => e.boundingBox match {
      case Circle(point2D, r) =>e match{
        case _: EggsImpl => g.setColor(Color.DARK_GRAY)
        case _: LarvaImpl => g.setColor(Color.MAGENTA)
        case _: PuppaImpl => g.setColor(Color.blue)
        case _: ButterflyImpl => g.setColor(Color.RED)
        case _ => g.setColor(Color.BLACK)
      }
        g.fillOval(modelToViewRatio(point2D.x - r, this.getSize().width, world.width),
          modelToViewRatio(point2D.y - r, this.getSize().height, world.height),
          modelToViewRatio(r * 2, this.getSize().width, world.width),
          modelToViewRatio(r * 2, this.getSize().height, world.height))
      case Rectangle(point2D, w, h) => e match{
        case _: PredatorImpl =>g.setColor(Color.black)
        case _  =>g.setColor(Color.green)
      }
        g.fillRect(modelToViewRatio(point2D.x - w / 2, this.getSize().width, world.width),
          modelToViewRatio(point2D.y - h / 2, this.getSize().height, world.height),
          modelToViewRatio(w, this.getSize().width, world.width),
          modelToViewRatio(h, this.getSize().height, world.height))
      case Triangle(point2D, h, a) => e match {
        case _ : NectarPlant=>g.setColor(Color.green)
        case _: FlourPlant =>g.setColor(Color.yellow)
      }
        val vertices = triangleVertices(Triangle(point2D, h, a))
        g.fillPolygon(vertices.productIterator.map({
          case p: Point2D => modelToViewRatio(p.x, this.getSize().width, world.width)
        }).toArray, vertices.productIterator.map({
          case p: Point2D => modelToViewRatio(p.y, this.getSize().height, world.height)
        }).toArray, vertices.productIterator.length)
    })
  }

  override def getPreferredSize: Dimension = {
    val borderValue = 15
    new Dimension(getParent.getSize().width - borderValue, getParent.getSize().height - borderValue)
  }



  private def triangleVertices(tri: Triangle): (Point2D, Point2D, Point2D) = {
    val radius = tri.height / 3 * 2
    (Point2D(tri.point.x, tri.point.y + radius), Point2D(tri.point.x - radius, tri.point.y - radius),
      Point2D(tri.point.x + radius, tri.point.y - radius))
  }


  private def modelToViewRatio(modelProperty: Double, viewDimension: Double, modelDimension: Double): Int =
    (modelProperty * viewDimension / modelDimension).round.toInt

}
