import javafx.beans.binding.Bindings
import model.{BoundingBox, Point2D}
import model.CreatureImpl.EggsImpl
import model.creature.creatureStructure.Butterfly
import model.reaction.reaction
import model.reaction.reaction.DEF_FOOD_ENERGY
import org.scalatest.funsuite.AnyFunSuite




class ReactionTest extends AnyFunSuite {

  //def collisionbetweennButterflyAndPLan(): Boolean = ???

  test("Test Butterfly reaction  when collide  with plants entities ") {
    //test.unsafeRunSync()
     val eggs: EggsImpl = EggsImpl(
       name = "egg1",
       boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
       direction = 14,
       fieldOfViewRadius = 10,
       velocity = 3,
       life = 100,
     )
    val egg: EggsImpl = EggsImpl(
      name = "egg2",
      boundingBox = BoundingBox.Circle(point = Point2D(100, 100), radius = 10),
      direction = 14,
      fieldOfViewRadius = 10,
      velocity = 3,
      life = 100,
    )
    //assert(Bindings.notEqual())
    //assert(reaction.plantFoodEffect(eggs).equals(Set(eggs.copy())))
    assert(reaction.collisionbetweennButterflyAndPLan(eggs).equals(Set(eggs.copy(life = eggs.life + DEF_FOOD_ENERGY))))
  }








}
