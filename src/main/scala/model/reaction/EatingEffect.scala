package model.reaction

import model.common.BoundingBox.Circle
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.common.Final.{DEF_BLOB_RADIUS, DEF_FOOD_ENERGY, DEF_NECTARD_ENERGY, DEF_SIMPLE_PLANT_ENERGY, MIN_BLOB_RADIUS, NULL_ENERGY, REDUCE_LIFE, REDUCE_LIFE_Larva, REDUCE_LIFE_Puppa}
import model.common.{Final, MovingStrategies}
import model.creature.CreatureObject.{Butterfly}
import utils.TypeUtilities.SimulableEntity


object EatingEffect {


  /** Returns a value with variable range from an initial value.
   *
   * @param value value that determines the range
   * @return a value between value - range and value + range
   */
  private def randomValueChange(value: Int): Int = {
    val max: Int = (value * 1.5).toInt
    val min: Int = (value * 0.8).toInt
    new java.util.Random().nextInt(max + 1 - min) - min
  }

  def collisionREceptivePLan[A <:Butterfly] (butterfly: A):Set[SimulableEntity]= butterfly match {
    case butterfly : EggsImpl =>Set(butterfly.copy())
    case butterfly : PuppaImpl =>Set(butterfly.copy())
    case butterfly : LarvaImpl =>Set(butterfly.copy())
    case adults:ButterflyImpl  => Set(adults.copy(life = adults.life +DEF_NECTARD_ENERGY),spwanEggs(adults))
    case _ => Set()
  }
  def iscollidedWithNectarPlant[A <:Butterfly](adults :A): Set[SimulableEntity] = adults match{
    case adults:ButterflyImpl  => Set(spwanEggs(adults))
    case butterfly : EggsImpl =>Set(butterfly.copy())
    case butterfly : PuppaImpl =>Set(butterfly.copy())
    case butterfly : LarvaImpl =>Set(butterfly.copy())

    case _ => Set()
  }
  /**
   * Returns a set with a copy of the Buttefly given as input with life incremented by Constants and a new Buttefly with properties based of the other Buttefly.
   *
   * @param buttefly a Buttefly subjected to the effect
   * @return a set with the Buttefly produced by the effect
   */
  def collideWithSimplePlan[A <: Butterfly](creature: A): Set[SimulableEntity]  = creature match {
    case larva : LarvaImpl => Set(larva.copy(life = creature.life + DEF_SIMPLE_PLANT_ENERGY))
    case puppa : PuppaImpl=> Set(puppa.copy(life = creature.life + DEF_SIMPLE_PLANT_ENERGY))
    case adults : ButterflyImpl=> Set(adults.copy(life = creature.life + DEF_SIMPLE_PLANT_ENERGY))
    case egg : EggsImpl => Set(egg.copy(life = creature.life + NULL_ENERGY))
    case _  => Set()

  }

  def spwanEggs[A <:Butterfly](adults :A): SimulableEntity =  adults match {
    case adults : ButterflyImpl => EggsImpl(name = adults.name + "-son"+Counter.nextValue,
      boundingBox = Circle(adults.boundingBox.point, randomValueChange(5).max(7)),
      movementStrategy = MovingStrategies.baseMovement,
      lifeCycle=adults.lifeCycle,
      changeStage = adults.changeStage,
      direction = adults.direction,
      velocity = adults.velocity-50,
      life = adults.life)
      case _ => ???//Set()
  }

  def simplePlantCollidedwithButterflyEntity[A <: Butterfly](butterfly: A):Set[SimulableEntity]= butterfly match {
    case egg : EggsImpl =>Set(egg.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case lava : PuppaImpl =>Set(lava.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case catepillar : LarvaImpl =>Set(catepillar.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case adult : ButterflyImpl =>Set(adult.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case _ => Set()
  }


  /**Represent the effect of collision of and predactor with another creature in the system
   * */
  def collidedWithPredactor[A <:Butterfly](creature: A) :Set[SimulableEntity] = creature  match{
    case e:EggsImpl => Set(e.copy(life = e.life - Final.REDUCE_LIFE))
    case e :PuppaImpl => Set(e.copy(life = e.life - REDUCE_LIFE_Puppa))
    case e: LarvaImpl => Set(e.copy(life = e.life - REDUCE_LIFE_Larva))
    case e: ButterflyImpl => Set(e.copy(life = e.life - REDUCE_LIFE))
    case _ => Set()
  }

  object Counter {

    private var index = 0

    def nextValue(): Int = {
      index += 1
      index
    }
  }
}





