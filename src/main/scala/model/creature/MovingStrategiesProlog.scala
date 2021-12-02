package model.creature

import alice.tuprolog.{Double, Int, Struct, Term, Var}
import model.common.Point2D
import model.creature.CreatureObject.Intelligent
import model.creature.PrologEngine.engine

import scala.language.implicitConversions

object MovingStrategiesProlog {
  val MAX_STEP_FOR_ONE_DIRECTION = 50
  val ITERATION_LAPSE: Double = 0.05

  implicit def termToInt(t: Term): scala.Int = t.toString.toInt

  implicit def toPrologInt(value: scala.Int): Int = new Int(value)

  implicit def toPrologDouble(value: scala.Double): Double = new Double(value)

  implicit def toStructPoint(point: Point2D): Term = new Struct("point", new Int(point.x), new Int(point.y))

  implicit def termToPoint2D(t: Term): Point2D = Point2D(extractVarValue(t, 0), extractVarValue(t, 1))

  implicit def termToDirection(t: Term): Direction = Direction(extractVarValue(t, 0), extractVarValue(t, 1))

  def standardMovement(entity: Intelligent, worldDimension: (scala.Int, scala.Int)): Movement = {

    val constantTerm = simulationConstantTerm(worldDimension) //Term containing the constant values needed to calculate the new position
    val pointVal = new Var("Point")
    val directionVal = new Var("Direction")
    val goal: Term = new Struct("standardMov", entity.boundingBox.point, entity.velocity,
      entity.direction.angle, entity.direction.stepToNextDirection, constantTerm,
      pointVal, directionVal)

    newPosition(goal, pointVal, directionVal)

  }

  def chaseMovement(entity: Intelligent, chasedEntity: Point2D, worldDimension: (scala.Int, scala.Int)): Movement = {
    val constantTerm = simulationConstantTerm(worldDimension)
    val pointVal = new Var("Point")
    val directionVal = new Var("Direction")
    val goal: Term = new Struct("chaseMov", entity.boundingBox.point, chasedEntity, entity.velocity, constantTerm,
      pointVal, directionVal)

    newPosition(goal, pointVal, directionVal)
  }

  private def newPosition(goal: Term, pointVar: Var, directionVar: Var): Movement = {
    val solution = engine(goal)
    val solveInfo = solution.iterator.next()
    Movement(solveInfo.getVarValue(pointVar.getName), solveInfo.getVarValue(directionVar.getName))
  }

  private def simulationConstantTerm(worldDimension: (scala.Int, scala.Int)): Term =
      new Struct("simulationConstants", scala.math.Pi, MAX_STEP_FOR_ONE_DIRECTION, worldDimension._1, worldDimension._2, ITERATION_LAPSE)

  private def extractVarValue(term: Term, argNumber: scala.Int): Term = term.asInstanceOf[Struct].getArg(argNumber)

}
