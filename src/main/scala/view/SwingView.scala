package view

import cats.data.EitherT.pure
import cats.effect.IO
import model.World
import model.common.Environment

import java.awt.FlowLayout
import javax.swing._
import scala.concurrent.Promise
import scala.concurrent.duration.Duration
//import view.SwingView.SimulationViewSwing.inputviewSwing.inputView
import view.SwingView.inputviewSwing.inputView
import javax.swing.JFrame


import java.awt.GridLayout
import javax.swing.JOptionPane.{OK_CANCEL_OPTION, OK_OPTION, PLAIN_MESSAGE, showConfirmDialog}
import javax.swing.{JComboBox, JLabel, JPanel, JTextField}
import scala.concurrent.{Await, Promise}


object SwingView extends Views {
  val frame = new JFrame("evo-sim")

  import javax.swing.JFrame

  val frameM = new JFrame("JFrame Example")
  //frame.add(panel)

  import javax.swing.JPanel

  val panel = new JPanel

  override def inputReadFromUser(): Environment = ???

  /*
  for {
    environmentPromise <- IO pure Promise[SimulationSettings]()
    mainPanel <- inputView(environmentPromise)
    cp<- IO {frame.setSize(mainPanel)}
    _ <-frame.add(cp)
    environment <- IO(Await.result(environmentPromise.future, Duration.Inf))
  } yield environment*/

    /*
    * {
    showConfirmDialog(null, panel, "Butterfly-Life-cycle", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {
      case OK_OPTION => Option(SimulationSettings(Eggs, temporalGranularity,
        comboIterations.getSelectedItem match {
          case "infinite" => Int.MaxValue
          case value => value.toString.toInt
        }, (comboMatrix, comboMatrix)))
      case _ => Option.empty
    }

    implicit def numberFrom(component: JTextField): Int = component.getText toInt

    implicit def numberFrom[T](component: JComboBox[T]): Int = component.getSelectedItem.toString toInt
  }*/

  override def simulationresult(): Int = ???

  override def statisticRisult(): Int = ???


  object inputviewSwing{
    println("inputviewSwing")

    private val MatrixSize = Array("150", "200", "500")
    private val Iterations = Array("1000", "5000", "infinite")
    private val DefaultColoniesNumber = 1
    private val DefaultTemporalGranularity = 1
//inputSetting:Promise[SimulationSettings]
    def inputView(inputSetting:Promise[SimulationSettings]):IO[JPanel]= for {
      mainPanel <- IO{new JPanel(new GridLayout(0, 1))}
      _ <- IO{mainPanel.add(new JLabel("N° Eggs:"))}
      eggs <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(eggs)}
      _ <- IO{mainPanel.add(new JLabel("N° Pupa:"))}
      pupa <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(pupa)}

      _ <- IO{mainPanel.add(new JLabel("N° Butterfly:"))}
      butterfly <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(butterfly)}

      _ <- IO{mainPanel.add(new JLabel("N° Obstacle:"))}
      obstacle <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(obstacle)}

      _ <- IO{mainPanel.add(new JLabel("N° Plan:"))}
      plan <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(plan)}

      _ <- IO{mainPanel.add(new JLabel("Temporal Granularity (days) :"))}
      temporalGranularity <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(temporalGranularity)}

      _ <- IO{mainPanel.add(new JLabel("N° max of Days:"))}
      comboIterations <-IO{new JTextField(DefaultColoniesNumber toString)}
      _ <- IO{mainPanel.add(comboIterations)}

      _ <- IO{mainPanel.add(new JLabel("Environment size:"))}
      comboIterations <-IO{new JComboBox(Iterations)}
      _ <- IO{mainPanel.add(comboIterations)}

      _ <- IO{mainPanel.add(new JLabel("N° max of Days:"))}
      comboMatrix <-IO{new JComboBox(MatrixSize)}
      _ <- IO{mainPanel.add(comboMatrix)}

      _ <- IO{mainPanel.add(new JLabel("N° max of Days:"))}
      comboIterations <-IO{new JComboBox(Iterations)}
      _ <- IO{mainPanel.add(comboIterations)}
      //_ <- IO { inputSetting.success(SimulationSettings(eggs, pupa, butterfly, obstacle,plan,temporalGranularity,comboIterations,comboIterations,comboMatrix,comboIterations))}
      _ <-IO {println("toto")}
      _ <- IO{showConfirmDialog(null, mainPanel, "Settings", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {
      case OK_OPTION => Option(SimulationSettings(eggs, temporalGranularity,
      comboIterations.getSelectedItem match {
      case "infinite" => Int.MaxValue
      case value => value.toString.toInt
    },temporalGranularity,comboIterations,comboIterations,comboMatrix,comboIterations,comboIterations,(comboMatrix, comboMatrix)))
    case _ => Option.empty
    }}
    }yield  mainPanel

    println("func")
    private implicit def numberFrom(component: JTextField): Int = component.getText toInt

    private implicit def numberFrom[T](component: JComboBox[T]): Int = component.getSelectedItem.toString toInt
  }
  /*
  *
      val panel = new JPanel(new GridLayout(0, 1))
      //val frame = new Frame(("Butterfly-Life-cycle"))
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

      showConfirmDialog(null, panel, "Butterfly-Life-cycle", OK_CANCEL_OPTION, PLAIN_MESSAGE) match {
        case OK_OPTION => Option(SimulationSettings(Eggs, temporalGranularity,
          comboIterations.getSelectedItem match {
            case "infinite" => Int.MaxValue
            case value => value.toString.toInt
          }, (comboMatrix, comboMatrix)))
        case _ => Option.empty
      }
  *
  * */
  object SimulationViewSwing{
    //def inputView():Int = ???

  }
  object StatisticalViewSwing{
    //def inputView():Int = ???

  }

  override def createAndShow(): Environment = ???

  override def simulationResult(world: World): Unit = ???
}













