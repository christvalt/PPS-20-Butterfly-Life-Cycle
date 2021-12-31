import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.common.Final.{STANDARD_LIFE_DECREASE, STANDARD_LIFE_INCREASE}
import model.common.{BoundingBox, Direction, MovingStrategies, Point2D}
import model.reaction.{BeingEatenEffect, DegenerationE}
import org.scalatest.funspec.AnyFunSpec

class Degeneration  extends AnyFunSpec {
  val DEFAULD_BLOB_LIFE = 50
  val LIFE_AFTER_DEGENERATION = 45

  val eggs: EggsImpl = EggsImpl(
    name = "egg1",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
  //  fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0,
    changeStage=DegenerationE.inc
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle = 0,
    changeStage = DegenerationE.inc
  )

  val Larva: LarvaImpl = LarvaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
   // fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )
  val adultB: ButterflyImpl = ButterflyImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
   // fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )
  describe("Standard Degradation effect") {
    it("should produce the reduction of its life") {
      val newLarva = adultB.copy(life =DegenerationE.deacreaseLifeEffect(adultB))
      assert(newLarva.life == adultB.life - STANDARD_LIFE_DECREASE)
    }

    it("should reduce egg life") {
      val newLarva = eggs.copy(life = DegenerationE.deacreaseLifeEffect(eggs))
      assert(newLarva.life == eggs.life - STANDARD_LIFE_DECREASE)
    }

    it("should reduce larva  life") {
      val newLarva = Larva.copy(life =DegenerationE.deacreaseLifeEffect(Larva))
      assert(newLarva.life == Larva.life - STANDARD_LIFE_DECREASE)
    }

    it("should reduce puppa  life") {
      val newLarva = puppa.copy(life =DegenerationE.deacreaseLifeEffect(puppa))
      assert(newLarva.life == puppa.life - STANDARD_LIFE_DECREASE)
    }

    it("should update lifecycle") {
      val newLarva = puppa.copy(lifeCycle = DegenerationE.inc(puppa))
      assert(newLarva.lifeCycle == puppa.lifeCycle + STANDARD_LIFE_INCREASE)
    }


  }
}
