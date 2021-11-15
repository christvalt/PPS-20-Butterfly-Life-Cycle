import model.CreatureImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.{BoundingBox, Point2D}
import model.reaction.{BeingEatenEffect, DegenerationE}
import org.scalatest.funspec.AnyFunSpec

class Degeneration  extends AnyFunSpec {
  val DEFAULD_BLOB_LIFE = 50
  val LIFE_AFTER_DEGENERATION = 40

  val eggs: EggsImpl = EggsImpl(
    name = "egg1",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )

  val Larva: LarvaImpl = LarvaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )
  val adultB: ButterflyImpl = ButterflyImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )
  describe("Standard Degradation effect") {
    it("should produce the reduction of its life") {
      val newLarva=adultB.copy(life =DegenerationE.deacreaseLifeEffect(adultB))
      assert(newLarva.life == LIFE_AFTER_DEGENERATION)
    }
  }
}
