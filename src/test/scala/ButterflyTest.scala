import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlourPlant, LarvaImpl, PuppaImpl}
import model.creature.CreatureObject.Butterfly
import model.reaction.EatingEffect.DEF_FOOD_ENERGY
import model.common.{BoundingBox, Direction, MovingStrategies, Point2D}
import model.reaction.{BeingEatenEffect, DegenerationE, EatingEffect}
import org.scalatest.funspec.AnyFunSpec

class ButterflyTest extends AnyFunSpec {

  val DEFAULD_BLOB_LIFE = 50
  val LIFE_AFTER_DEGENERATION = 40

  val adultB: ButterflyImpl = ButterflyImpl(
    name = "adultB",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  val lava: LarvaImpl = LarvaImpl(
    name = "adultB",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  val egg: EggsImpl = EggsImpl(
    name = "egg",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "puppa",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )

  private val food: FlourPlant = FlourPlant(
    name = "food4",
    boundingBox = BoundingBox.Triangle(point = Point2D(100, 100), height = 10),
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    life = 100,
    collisionEffect = EatingEffect.simplePlantCollidedwithButterflyEntity
  )

  describe("in every cycle of creature when it's collide with a colliding entities") {
    it("the butterfly  should eat the plant"){
      assert(adultB.collision(food).exists{
        case b: Butterfly => b.life == adultB.life + DEF_FOOD_ENERGY
        case _ => false
      })
    }
  }
}

