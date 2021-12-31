import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.common.Final.{DEF_FOOD_ENERGY, DEF_SIMPLE_PLANT_ENERGY, NULL_ENERGY, REDUCE_LIFE, REDUCE_LIFE_Larva, REDUCE_LIFE_Puppa}
import model.common.{BoundingBox, Direction, MovingStrategies, Point2D}
import model.reaction.{BeingEatenEffect, EatingEffect}
import org.scalatest.funspec.AnyFunSpec


class CollitionTest extends AnyFunSpec {

  val DEFAULD_BLOB_LIFE = 50

  val eggs: EggsImpl = EggsImpl(
    name = "egg1",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
   // fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    //fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )

  val Larva: LarvaImpl = LarvaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = Direction(0, 15),
    //fieldOfViewRadius = 10,
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
    //fieldOfViewRadius = 10,
    velocity = 3,
    life = DEFAULD_BLOB_LIFE,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect,
    movementStrategy = MovingStrategies.baseMovement,
    lifeCycle=0
  )

  describe("Collision  Effect apply to a Egg") {
    describe("when egg collide with simple plant and predactor ") {

      it("increase the life of the eggs and would be greatheet that the previeos value") {
        assert(EatingEffect.simplePlantCollidedwithButterflyEntity(eggs).equals(Set(eggs.copy(life = eggs.life + DEF_FOOD_ENERGY))))
      }

      it("egg life  value") {
        assert(EatingEffect.collideWithSimplePlan(eggs).equals(Set(eggs.copy(life = eggs.life + NULL_ENERGY ))))
      }

      it("dicrease the life of the egg and would be small than the previeos value ") {
        assert(EatingEffect.collidedWithPredactor(eggs).equals(Set(eggs.copy(life = eggs.life- REDUCE_LIFE))))

      }
    }

  }

  describe("kvfv"){
    // LARVA Effect
    it("larva and predactor dicrese") {
      assert(EatingEffect.collidedWithPredactor(Larva).equals(Set(Larva.copy(life = Larva.life - REDUCE_LIFE_Larva))))
    }

    it("increase the life of Larva when collide with a simple plan ") {
      assert(EatingEffect.collideWithSimplePlan(Larva).equals(Set(Larva.copy(life = Larva.life + DEF_SIMPLE_PLANT_ENERGY))))
    }


  }
  describe("pu"){
    // PUPPA Efect


    it("increase the life of adult buuuterflay when collide with a simple plan ") {
      assert(EatingEffect.collideWithSimplePlan(puppa).equals(Set(puppa.copy(life = puppa.life+ DEF_SIMPLE_PLANT_ENERGY))))
    }

    it("increase the life of larva when collide with a simple plan ") {
      assert(EatingEffect.collidedWithPredactor(puppa).equals(Set(puppa.copy(life = puppa.life - REDUCE_LIFE_Puppa))))

    }
  }

/*
*
*   describe("BUtte"){
    //BUTTERFLY Effect
    it("increase the life of puppa when collide with a simple plan ") {
      assert(EatingEffect.collideWithSimplePlan(adultB).equals(Set(adultB.copy(life = adultB.life + DEF_SIMPLE_PLANT_ENERGY))))
    }

    it("increase the life of puppa when collide with a simple plan ") {
      assert(EatingEffect.collidedWithPredactor(adultB).equals(Set(adultB.copy(life = adultB.life -  REDUCE_LIFE))))

    }
  }*/


}
