import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
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
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    //lifeCycle=0
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
   // fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )

  val Larva: LarvaImpl = LarvaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
   // fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  val adultB: ButterflyImpl = ButterflyImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
   // fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  describe("Standard Degradation effect") {
    it("should produce the reduction of its life") {
      val newLarva=adultB.copy(life =DegenerationE.deacreaseLifeEffect(adultB))
      assert(newLarva.life == LIFE_AFTER_DEGENERATION)
    }
  }
}
