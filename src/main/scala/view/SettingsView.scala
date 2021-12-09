package view

import cats.effect.IO
import model.World
import model.common.Environment
import view.SwingView.frame

import java.awt.event.{WindowAdapter, WindowEvent}
import java.awt.{BorderLayout, Dimension, Frame, GraphicsEnvironment, GridLayout, Toolkit}
import javax.swing.JOptionPane._
import javax.swing._
import scala.concurrent.{Await, Promise}
import scala.concurrent.duration.Duration


/**
 * View for setup simulation.
 */
object SettingsView extends Views {
  private val Iterations = Array("10", "50", "100")
  private val DefaultColoniesNumber = 1
  private val DefaultTemporalGranularity = 1

  val frame = new  JFrame ("Butterfly LFC")



  private val userInput: Promise[Environment] = Promise[Environment]()


  override def createAndShow: Unit = {

    val panel = new JPanel(new GridLayout(0, 1))

    panel.add(new JLabel("N° Butterfly:"))
    val Butterfly = new JTextField(DefaultColoniesNumber toString)
    panel.add(Butterfly)

    panel.add(new JLabel("N° predator:"))
    val predator = new JTextField(DefaultColoniesNumber toString)
    panel.add(predator)

    panel.add(new JLabel("N° Plan:"))
    val Plan = new JTextField(DefaultColoniesNumber toString)
    panel.add(Plan)

    panel.add(new JLabel("Temporal Granularity (days) :"))
    val temporalGranularity = new JTextField(DefaultTemporalGranularity toString)
    panel.add(temporalGranularity)

    panel.add(new JLabel("N° max of Days:"))
    val dayNumber = new JComboBox(Iterations)
    panel.add(dayNumber)



     showConfirmDialog(null, panel, "Settings", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {
      case OK_OPTION =>userInput.success(Environment(Butterfly,predator,Plan, temporalGranularity,
        dayNumber.getSelectedItem match {
          case "infinite" => Int.MaxValue
          case value => value.toString.toInt
        }))
      case _ =>  Environment(Butterfly ,predator,Plan , temporalGranularity,dayNumber)
    }


      ///userInput.success(Environment(temperature = Butterfly.getValue, buttefly = ???, plant = ???, predator = ???, days = ???))
  }



  def getInputUser ():Environment  = Await.result(userInput.future, Duration.Inf)


  private implicit def numberFrom(component: JTextField): Int = component.getText toInt

  private implicit def numberFrom[T](component: JComboBox[T]): Int = component.getSelectedItem.toString toInt


  override def simulationViewCrateAndShowed(): Unit  =  {
    frame.getContentPane.add(entityPanel, BorderLayout.CENTER)
    frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.width))
    frame.setResizable(false)
   //frame.getContentPane().add(shape)
    frame.setDefaultCloseOperation(3)
    frame.pack()
    frame.setVisible(true)
  }


  private val entityPanel = new JPanel

  override def rendered(world: World): Unit = {
    SwingUtilities.invokeAndWait(() => {
      entityPanel.removeAll()
      entityPanel.add(new ShapesPanel(world))
      frame.pack()
    })
  }




  object SimulationViewTeest extends  JPanel{



  }
}
