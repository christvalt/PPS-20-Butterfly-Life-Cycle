package view

import cats.effect.IO

import java.awt.{Frame, GridLayout}
import javax.swing.JOptionPane._
import javax.swing._


/**
 * View for setup simulation.
 */
private[view] object SettingsView extends Views {
  private val MatrixSize = Array("100", "200", "300")
  private val Iterations = Array("1000", "5000", "infinite")
  private val DefaultColoniesNumber = 1
  private val DefaultTemporalGranularity = 1

   def createAndShow: Option[SimulationSettings] = {

    //val frame = new Frame(("Butterfly-Life-cycle"))
    val panel = new JPanel(new GridLayout(0, 1))

    panel.add(new JLabel("N° Eggs:"))
    val Eggs = new JTextField(DefaultColoniesNumber toString)
    panel.add(Eggs)

    panel.add(new JLabel("N° Larva:"))
    val Larva = new JTextField(DefaultColoniesNumber toString)
    panel.add(Larva)

    panel.add(new JLabel("N° Pupa:"))
    val Pupa = new JTextField(DefaultColoniesNumber toString)
    panel.add(Pupa)


    panel.add(new JLabel("N° Butterfly:"))
    val Butterfly = new JTextField(DefaultColoniesNumber toString)
    panel.add(Butterfly)

    panel.add(new JLabel("N° Obstacle:"))
    val Obstacle = new JTextField(DefaultColoniesNumber toString)
    panel.add(Obstacle)

    panel.add(new JLabel("N° Plan:"))
    val Plan = new JTextField(DefaultColoniesNumber toString)
    panel.add(Plan)

    panel.add(new JLabel("Temporal Granularity (days) :"))
    val temporalGranularity = new JTextField(DefaultTemporalGranularity toString)
    panel.add(temporalGranularity)

    panel.add(new JLabel("N° max of Days:"))
    val comboIterations = new JComboBox(Iterations)
    panel.add(comboIterations)

    panel.add(new JLabel("Environment size:"))
    val comboMatrix = new JComboBox(MatrixSize)
    panel.add(comboMatrix)

    showConfirmDialog(null, panel, "Settings", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {
      case OK_OPTION => Option(SimulationSettings(Butterfly, temporalGranularity,
        comboIterations.getSelectedItem match {
          case "infinite" => Int.MaxValue
          case value => value.toString.toInt
        }, temporalGranularity,comboIterations,comboIterations,comboMatrix,comboIterations,comboIterations,(comboMatrix, comboMatrix)))
      case _ => Option.empty
    }
  }

  private implicit def numberFrom(component: JTextField): Int = component.getText toInt

  private implicit def numberFrom[T](component: JComboBox[T]): Int = component.getSelectedItem.toString toInt



  override def inputReadFromUser(): IO[SimulationSettings] = ???

  override def simulationresult(): Int = ???

  override def statisticRisult(): Int = ???
}
