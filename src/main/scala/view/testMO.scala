/*
* package view
import cats.effect.IO
import view.SettingsView.{DefaultColoniesNumber, DefaultTemporalGranularity, Iterations, MatrixSize}

import java.awt.{Frame, GridLayout}
import javax.swing.JOptionPane.{OK_CANCEL_OPTION, OK_OPTION, PLAIN_MESSAGE, showConfirmDialog}
import javax.swing.{JComboBox, JLabel, JPanel, JTextField}

object testMO extends Views {
  override def inputReadFromUser(): SimulationSettings = ???


  object viewsUtils{
    object inputviewsSwing{
      private val MatrixSize = Array("100", "200", "300")
      private val Iterations = Array("1000", "5000", "infinite")
      private val DefaultColoniesNumber = 1
      private val DefaultTemporalGranularity = 1


    /*


     def testUser(settingsView: SimulationSettings):SimulationSettings={
        val frame = new Frame(("Butterfly-Life-cycle"))
        val panel = new JPanel(new GridLayout(0, 1))

        panel.add(new JLabel("N° Eggs:"))
        val Eggs = new JTextField(DefaultColoniesNumber toString)
        panel.add(Eggs)

      }
    * */


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
          }, (comboMatrix, comboMatrix)))
        case _ => Option.empty
      }
    }

    private implicit def numberFrom(component: JTextField): Int = component.getText toInt

    private implicit def numberFrom[T](component: JComboBox[T]): Int = component.getSelectedItem.toString toInt

  }

  override def simulationresult(): Int = ???

  override def statisticRisult(): Int = ???
}

*
* */