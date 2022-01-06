import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlowerPlant, LarvaImpl, PredatorImpl, PuppaImpl}
import model.World
import model.common.Final.{BUTTERFLY_VELOCITY, DEF_SIMPLE_PLANT_ENERGY, LIFE_ADD_EGG_TO_LARVA, REDUCE_LIFE_Larva, REDUCE_LIFE_Puppa, VELOCITY_ADD_EGG_TO_LARVA}
import model.creature.CreatureObject.Butterfly
import model.common.{BoundingBox, Direction, MovingStrategies, Point2D}
import model.reaction.DegenerationE.helperEggToLarva
import model.reaction.{BeingEatenEffect, DegenerationE, EatingEffect}
import org.scalatest.funspec.AnyFunSpec


class ButterflyTest extends AnyFunSpec {

  val DEFAULD_BLOB_LIFE = 50
  val LIFE_AFTER_DEGENERATION = 40

  private val adultB: ButterflyImpl = ButterflyImpl(
    name = "adultB",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )
  private val lava: LarvaImpl = LarvaImpl(
    name = "larva",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 700,
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )
  private val egg: EggsImpl = EggsImpl(
    name = "egg",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 1200,
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )
  private val puppa: PuppaImpl = PuppaImpl(
    name = "puppa",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    velocity = 3,
    life = 100,
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )


  private val food: FlowerPlant = FlowerPlant(
    name = "food4",
    boundingBox = BoundingBox.Triangle(point = Point2D(100, 100), height = 10),
    degradationEffect = DegenerationE.deacreaseLifeEffect,
    life = 100,
    collisionEffect = EatingEffect.collideWithSimplePlan,
    lifeCycle=0
  )

  private val predator : PredatorImpl = PredatorImpl(
    name = "predator",
    boundingBox = BoundingBox.Rectangle(point = Point2D(100, 100), width = 2, height = 2),
    collisionEffect =EatingEffect.collidedWithPredactor,
    degradationEffect =DegenerationE.deacreaseLifeEffect,
    life = 5003,
    lifeCycle = 0,
    direction = Direction(20, 10),
    velocity = BUTTERFLY_VELOCITY,
    movementStrategy = MovingStrategies.baseMovement,
  )

  private val world:World = World(temperature=12,
    width =12,
    height =12,
    creature =Set(egg,adultB ,puppa ,food,predator),
    currentIteration =0,
    totalIterations=10)



  describe(" During all the cycle of eggs when it's collide with a colliding entities") {
    it(" if the collinding entity is an predator , it' life  should deacrese "){

      val collidedEgg = egg.collision(predator).head.asInstanceOf[Butterfly]
      assert(collidedEgg.life < egg.life)
    }
    it("if the collinding entity is an an simple food , it' life should be the same "){
      val collidedEggPla = egg.collision(food).head.asInstanceOf[Butterfly]
      assert(collidedEggPla.life == collidedEggPla.life)
    }
  }


  describe(" During all the cycle of Larva  when it's collide with a colliding entities") {
    it(" if the collinding entity is an predator , it' life  should deacrese "){
      val collidedEgg = lava.collision(predator).head.asInstanceOf[Butterfly]
      assert(collidedEgg.life == lava.life - REDUCE_LIFE_Larva )
    }
    it("if the collinding entity is an an simple food , it's life should be increase "){
      val collidedEggPla = lava.collision(food).head.asInstanceOf[Butterfly]
      assert(collidedEggPla.life == lava.life + DEF_SIMPLE_PLANT_ENERGY)
    }
  }


  describe(" During all the cycle of puppa  when it's collide with a colliding entities") {
    it(" if the collinding entity is an predator , it' life  should deacrese "){
      val collidedEgg = puppa.collision(predator).head.asInstanceOf[Butterfly]
      assert(collidedEgg.life == puppa.life - REDUCE_LIFE_Puppa )
    }
    it("if the collinding entity is an an simple food , it's life should be increase "){
      val collidedEggPla = puppa.collision(food).head.asInstanceOf[Butterfly]
      assert(collidedEggPla.life == puppa.life + DEF_SIMPLE_PLANT_ENERGY)
    }
  }


  describe("Egg with EggBehavior") {
    describe("when transforming to be a larva ") {
      it("the life cycle change"){
        val newEGGToLarva : LarvaImpl = helperEggToLarva(egg).asInstanceOf[LarvaImpl]
        assert(newEGGToLarva.life==egg.life+LIFE_ADD_EGG_TO_LARVA)
        assert(newEGGToLarva.velocity==egg.velocity+VELOCITY_ADD_EGG_TO_LARVA)
      }

    }

  }



  describe("Life during the updating stage of egg "){

    /*it("the new egg should have less life") {
      val partialUpdatedEgg = egg.updateState(world) //.head.asInstanceOf[EggsImpl]
      assert(partialUpdatedEgg.exists{
        case e :EggsImpl => e.life== egg.degradationEffect(egg)
      })
    }*/


  }
}

