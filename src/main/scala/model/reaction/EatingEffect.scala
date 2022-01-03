package model.reaction

import model.common.BoundingBox.Circle
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.common.Final.{DEF_BLOB_RADIUS, DEF_FOOD_ENERGY, DEF_NECTARD_ENERGY, DEF_SIMPLE_PLANT_ENERGY, MIN_BLOB_RADIUS, NULL_ENERGY, REDUCE_LIFE, REDUCE_LIFE_Larva, REDUCE_LIFE_Puppa}
import model.common.{Final, MovingStrategies}
import model.creature.CreatureObject.{Butterfly}
import utils.TypeUtilities.SimulableEntity


object EatingEffect {



  private def randomValueChange(value: Int): Int = {
    val max: Int = (value * 1.5).toInt
    val min: Int = (value * 0.8).toInt
    new java.util.Random().nextInt(max + 1 - min) - min
  }

  def collisionREceptivePLan[A <:Butterfly] (butterfly: A):Set[SimulableEntity]= butterfly match {
    case butterfly : EggsImpl =>Set(butterfly.copy())
    case butterfly : PuppaImpl =>Set(butterfly.copy())
    case butterfly : LarvaImpl =>Set(butterfly.copy())
    case butterfly : ButterflyImpl =>Set(butterfly.copy())
    case _ => Set()
  }
  def iscollidedWithNectarPlant[A <:Butterfly](adults :A): Set[SimulableEntity] = adults match{
    case adults:ButterflyImpl  => Set(adults.copy(life = adults.life +DEF_NECTARD_ENERGY),spwanEggs(adults))
    case _ => Set()
  }

  def collideWithSimplePlan[A <: Butterfly](creature: A): Set[SimulableEntity]  = creature match {
    case larva : LarvaImpl => Set(larva.copy(life = creature.life + DEF_SIMPLE_PLANT_ENERGY))
    case puppa : PuppaImpl=> Set(puppa.copy(life = creature.life + DEF_SIMPLE_PLANT_ENERGY))
    case adults : ButterflyImpl=> Set(adults.copy(life = creature.life + DEF_SIMPLE_PLANT_ENERGY))
    case egg : EggsImpl => Set(egg.copy(life = creature.life + NULL_ENERGY))
    case _  => Set()

  }

  def spwanEggs[A <:Butterfly](adults :A): SimulableEntity =  adults match {
    case adults :ButterflyImpl => EggsImpl(name = adults.name + "-son"+Counter.nextValue, boundingBox = Circle(adults.boundingBox.point, randomValueChange(DEF_BLOB_RADIUS).max(MIN_BLOB_RADIUS)),
      movementStrategy = MovingStrategies.baseMovement,lifeCycle=adults.lifeCycle,changeStage = ???,direction = ???,velocity = ???,life = ???)
      //fieldOfViewRadius = randomValueChange(DEF_BLOB_FOV_RADIUS).max(MIN_BLOB_FOV_RADIUS),movementStrategy = MovingStrategies.baseMovement)
      case _ => ???//Set()
  }

  def simplePlantCollidedwithButterflyEntity[A <: Butterfly](butterfly: A):Set[SimulableEntity]= butterfly match {
    case egg : EggsImpl =>Set(egg.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case lava : PuppaImpl =>Set(lava.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case catepillar : LarvaImpl =>Set(catepillar.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case adult : ButterflyImpl =>Set(adult.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case _ => Set()
  }

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





