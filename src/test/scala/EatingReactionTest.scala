import javafx.beans.binding.Bindings
import model.{BoundingBox, Point2D}
import model.CreatureImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.creature.creatureStructure.Butterfly
import model.creature.creatureStructure.Domain.DegradationEffect
import model.reaction.{BeingEatenEffect, EatingEffect}
import model.reaction.EatingEffect.{DEF_FOOD_ENERGY, REDUCE_LIFE, eatingReproducingOrNectarFoodEffect}
import org.scalatest.funsuite.AnyFunSuite


class EatingReactionTest extends AnyFunSuite {

  val eggs: EggsImpl = EggsImpl(
    name = "egg1",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )
  val puppa: PuppaImpl = PuppaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )

  val Larva: LarvaImpl = LarvaImpl(
    name = "egg2",
    boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )
  val adultB: ButterflyImpl = ButterflyImpl(
    name = "egg2",
    boundingBox = BoundingBox.Triangle(point = Point2D(100, 100), height = 10),
    direction = 14,
    fieldOfViewRadius = 10,
    velocity = 3,
    life = 100,
    degradationEffect =BeingEatenEffect.eatingByPredatorEffect
  )

  test("Test Butterfly reaction  when eating plant  with plants entities ") {
    //test.unsafeRunSync()
    assert(EatingEffect.eatingStandardPLantEffect(eggs).equals(Set(eggs.copy(life = eggs.life + DEF_FOOD_ENERGY))))
    assert(EatingEffect.collisionWithPredactor(eggs).equals(Set(eggs.copy(life =eggs.life- REDUCE_LIFE))))
    assert(EatingEffect.collisionREceptivePLan(eggs).equals(Set(eggs)))
    //assert(reaction.reproducingFoodForAdultButterfly(egg).equals(Set(eggs.copy())))
    //  assert(reaction.reproducingFoodForAdultButterfly(adultB).exists(Set(adultB.copy(life = adultB.life + DEF_FOOD_ENERGY))))

  }
  test("Adult Butterfly  should increase life  and create a adultbuterfly child(Eggs)"){
    //assert(reaction.reproducingFoodForAdultButterfly(adultB).equals().count()

  }
}
