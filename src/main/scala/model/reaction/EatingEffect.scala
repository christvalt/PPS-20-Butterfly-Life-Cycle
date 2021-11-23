package model.reaction

import model.BoundingBox.Circle
import model.SimulationObjectImpl
import model.SimulationObjectImpl.{ButterflyImpl, DEF_BLOB_FOV_RADIUS, EggsImpl, LarvaImpl, PredatorImpl, PuppaImpl}
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}


object EatingEffect {

  var DEF_BLOB_RADIUS: Int = 333
  var MIN_BLOB_RADIUS: Int = 444
  val MIN_BLOB_FOV_RADIUS: Int = 222
  var INTERVAL: Int = 1
  val DEF_FOOD_ENERGY = 50
  val REDUCE_LIFE = 25
  val DEF_NECTARD_ENERGY = 222

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

  def spwanEggs[A <:Butterfly](adults :A): SimulableEntity =  adults match {
    case adults :ButterflyImpl => EggsImpl(name = adults.name + "-son"+Counter.nextValue, boundingBox = Circle(adults.boundingBox.point, randomValueChange(DEF_BLOB_RADIUS).max(MIN_BLOB_RADIUS)),
      fieldOfViewRadius = randomValueChange(DEF_BLOB_FOV_RADIUS).max(MIN_BLOB_FOV_RADIUS))
      case _ => ???//Set()
  }

  def simplePlantCollidedwithButterflyEntity[A <: Butterfly](butterfly: A):Set[SimulableEntity]= butterfly match {
    case egg : EggsImpl =>Set(egg.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case lava : PuppaImpl =>Set(lava.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case catepillar : LarvaImpl =>Set(catepillar.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case adult : ButterflyImpl =>Set(adult.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case _ => Set()
  }

  def iscollidedWithPredactor[A <:Butterfly](eggs: A) :Set[SimulableEntity] = eggs  match{
    case e:EggsImpl => Set(e.copy(life = e.life - REDUCE_LIFE))
    case e :PuppaImpl => Set(e.copy(life = e.life - REDUCE_LIFE))
    case e: LarvaImpl => Set(e.copy(life = e.life - REDUCE_LIFE))
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





