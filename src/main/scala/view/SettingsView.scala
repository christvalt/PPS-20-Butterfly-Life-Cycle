package view

import cats.effect.IO
import model.World
import model.common.Environment

import java.awt.event.{WindowAdapter, WindowEvent}
import java.awt.{BorderLayout, Dimension, Frame, GraphicsEnvironment, GridLayout, Toolkit}
import javax.swing.JOptionPane._
import javax.swing._


/**
 * View for setup simulation.
 */
object SettingsView extends Views {
  private val MatrixSize = Array("100", "200", "300")
  private val Iterations = Array("1000", "5000", "infinite")
  private val DefaultColoniesNumber = 1
  private val DefaultTemporalGranularity = 1

    override def createAndShow: Environment = {

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
    val comboIterations = new JComboBox(Iterations)
    panel.add(comboIterations)


    showConfirmDialog(null, panel, "Settings", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {
      case OK_OPTION => (Environment(Butterfly,predator,Plan, temporalGranularity,
        comboIterations.getSelectedItem match {
          case "infinite" => Int.MaxValue
          case value => value.toString.toInt
        }))
      case _ => ???
    }
  }

  private implicit def numberFrom(component: JTextField): Int = component.getText toInt

  private implicit def numberFrom[T](component: JComboBox[T]): Int = component.getSelectedItem.toString toInt



  override def inputReadFromUser(): Environment = ???

  override def simulationresult(): Int = ???

  override def statisticRisult(): Int = ???

  override def simulationResult(world : World): Unit  =  {
    val frame = new  JFrame ("tett")
    frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit.getScreenSize.width, Toolkit.getDefaultToolkit.getScreenSize.width))
    frame.setResizable(false)
    val shape =  new ShapesPanel(world)
    frame.getContentPane().add(shape)
    frame.setDefaultCloseOperation(3)
    frame.pack()
    frame.setVisible(true)
  }




  object SimulationViewTeest extends  JPanel{



  }
}
