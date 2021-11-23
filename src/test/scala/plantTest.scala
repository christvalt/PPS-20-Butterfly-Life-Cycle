import model.SimulationObjectImpl.{ButterflyImpl, NectarPlant, flourPlant}
import model.{BoundingBox, Point2D}
import model.creature.CreatureObject.Butterfly
import model.reaction.EatingEffect.DEF_FOOD_ENERGY
import model.reaction.{BeingEatenEffect, DegenerationE, EatingEffect}
import org.scalatest.funspec.AnyFunSpec

class plantTest extends AnyFunSpec {

  val DEFAULD_BLOB_LIFE = 50
  val LIFE_AFTER_DEGENERATION = 40

  val adultB: ButterflyImpl = ButterflyImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )

  private val food: flourPlant = flourPlant(
    name = "food4",
    boundingBox = BoundingBox.Triangle(point = Point2D(100, 100), height = 10),
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    life = 100,
    collisionEffect = EatingEffect.simplePlantCollidedwithButterflyEntity
  )

  private val foods: NectarPlant = NectarPlant(
    name = "food4",
    boundingBox = BoundingBox.Triangle(point = Point2D(100, 100), height = 10),
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    life = 100,
    collisionEffect = EatingEffect.simplePlantCollidedwithButterflyEntity
  )
  private val reproductuvefood: NectarPlant = foods.copy(name = "food5", collisionEffect = EatingEffect.simplePlantCollidedwithButterflyEntity)

  describe("when a butterfly collide with a  simple plant") {
    it("the butterfly  should eat the plant and it's life is increase ") {
      assert(adultB.collision(food).exists {
        case b: Butterfly => b.life == adultB.life + DEF_FOOD_ENERGY
        case _ => false
      })
    }
  }

  describe("when a butterfly collide with a  nectarplant") {
    it("should increase the life of buttefly") {
      assert(adultB.collision(reproductuvefood).exists {
        case b: Butterfly => b.life == adultB.life + DEF_FOOD_ENERGY
        case _ => false
      })
    }
    it("should  create  new eggscreature") {
      assert(adultB.collision(reproductuvefood).size == 1)
    }
  }

}
