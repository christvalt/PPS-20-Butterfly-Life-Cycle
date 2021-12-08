import model.BoundingBox
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.common.Point2D
import model.creature.CreatureObject.Butterfly
import model.creature.CreatureObject.Domain.Degeneration
import model.creature.{Direction, MovingStrategies}
import model.reaction.{BeingEatenEffect, EatingEffect}
import model.reaction.EatingEffect.{DEF_FOOD_ENERGY, REDUCE_LIFE, simplePlantCollidedwithButterflyEntity}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.funsuite.AnyFunSuite


class CollitionTest extends AnyFunSpec {

  val DEFAULD_BLOB_LIFE = 50

  val eggs: EggsImpl = EggsImpl(
    name = "egg1",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )

  val Larva: LarvaImpl = LarvaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )
  val adultB: ButterflyImpl = ButterflyImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement
  )

  describe("PLant Effect appy to a butterfly") {
    describe("when  eating standars plant and when collide with a predactor ") {
      it("increase the life of the creature and would be greatheet that the previeos value") {
        assert(EatingEffect.simplePlantCollidedwithButterflyEntity(eggs).equals(Set(eggs.copy(life = eggs.life + DEF_FOOD_ENERGY))))
      }
      it("dicrease the life of the creature and would be greatheet that the previeos value ") {
        assert(EatingEffect.iscollidedWithPredactor(eggs).equals(Set(eggs.copy(life =eggs.life- REDUCE_LIFE))))

      }



      /*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   Test after*/
      it("should produce NoSuchElementException when head is invoked") {
       // assert(EatingEffect.collisionREceptivePLan(eggs).equals(Set(eggs)))
        //assert(EatingEffect.iscollidedWithNectarPlant(eggs).equals(Set(eggs)))
        //Adult Butterfly  should increase life  and create a adultbuterfly child(Eggs)
        //assert(reaction.reproducingFoodForAdultButterfly(egg).equals(Set(eggs.copy())))
        // assert(EatingEffect.collisionREceptivePLan(adultB).exists(Set(adultB.copy(life = adultB.life + DEF_FOOD_ENERGY))))
        //assert(reaction.reproducingFoodForAdultButterfly(adultB).equals().count()

      }
    }

    //assert(collisionREceptivePLan().exists(_.isEmpty))
  }


}
