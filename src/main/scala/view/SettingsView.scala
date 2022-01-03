package view

import model.World
import model.common.Environment
import view.graphic.ShapesPanel

import java.awt.{BorderLayout, Dimension, GridLayout, Toolkit}
import javax.swing.JOptionPane._
import javax.swing._
import scala.concurrent.{Await, Promise}
import scala.concurrent.duration.Duration


/**
 * View for setup simulation.
 */
object SettingsView extends Views {
  private val Iterations = Array("10", "50", "100")
  private val DefaultEggNumber = 10
  private val DefaultOtherNumber = 1
  private val DefaultPredatorNumber = 5
  private val DefaultPlantNumber = 65
  private val DefaultTemporalGranularity = 1

  val frame = new  JFrame ("Butterfly LFC")
  private val userInput: Promise[Environment] = Promise[Environment]
  private val entityPanel = new JPanel


  override def createAndShow: Unit = {

    val panel = new JPanel(new GridLayout(0, 2))

    panel.add(new JLabel("N° Eggs:"))
    val Eggs = new JTextField(DefaultEggNumber toString)
    panel.add(Eggs)

    panel.add(new JLabel("N° Puppa:"))
    val Puppa = new JTextField(DefaultOtherNumber toString)
    panel.add(Puppa)

    panel.add(new JLabel("N° Larva:"))
    val Larva = new JTextField(DefaultOtherNumber toString)
    panel.add(Larva)

    panel.add(new JLabel("N° Butterfly:"))
    val Butterfly = new JTextField(DefaultOtherNumber toString)
    panel.add(Butterfly)

    panel.add(new JLabel("N° predator:"))
    val predator = new JTextField(DefaultPredatorNumber toString)
    panel.add(predator)

    panel.add(new JLabel("N° Plan:"))
    val Plan = new JTextField(DefaultPlantNumber toString)
    panel.add(Plan)

    panel.add(new JLabel("Temporal Granularity (days) :"))
    val temporalGranularity = new JTextField(DefaultTemporalGranularity toString)
    panel.add(temporalGranularity)

    panel.add(new JLabel("N° max of Days:"))
    val dayNumber = new JComboBox(Iterations)
    panel.add(dayNumber)


     showConfirmDialog(null, panel, "Settings", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {

      case OK_OPTION =>userInput.success(Environment(temperature=temporalGranularity.getText.toInt,
        buttefly = Butterfly.getText.toInt,
        eggs = Eggs.getText.toInt,puppa = Puppa.getText.toInt,
        larva = Larva.getText.toInt,plant = Plan.getText.toInt,
        predator=predator.getText.toInt,
        dayNumber.getSelectedItem match {
          case "infinite" => Int.MaxValue
          case value => value.toString.toInt
        }))
      case _ =>  Environment(temporalGranularity.getText.toInt, Butterfly.getText.toInt,Eggs.getText.toInt,Puppa.getText.toInt,larva = Larva.getText.toInt, Plan.getText.toInt, predator.getText.toInt,dayNumber)
    }
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


  override def rendered(world: World): Unit = {
    SwingUtilities.invokeAndWait(() => {
      entityPanel.removeAll()
      entityPanel.add(new ShapesPanel(world))
      frame.pack()
    })
  }

}
