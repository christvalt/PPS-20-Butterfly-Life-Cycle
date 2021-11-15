package model.reaction

import model.BoundingBox.Circle
import model.CreatureImpl
import model.CreatureImpl.{ButterflyImpl, DEF_BLOB_FOV_RADIUS, EggsImpl, LarvaImpl, PuppaImpl}
import model.creature.creatureStructure.Butterfly
import model.{BoundingBox, Point2D}

import scala.Byte.MaxValue

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

  //stat to the corect place ---->FOODS
  def collisionREceptivePLan[A <:Butterfly] (butterfly: A):Set[Butterfly]= butterfly match {
    case butterfly : EggsImpl =>Set(butterfly.copy())
    case butterfly : PuppaImpl =>Set(butterfly.copy())
    case butterfly : LarvaImpl =>Set(butterfly.copy())
    case butterfly : ButterflyImpl =>Set(butterfly.copy())
    case _ => Set()
  }
  //---->FOODS    II FOndamnetal 2
  def iscollidedWithNectarPlant[A <:Butterfly](adults :A): Set[Butterfly] = adults match{
    case adults:ButterflyImpl  => Set(adults.copy(life = adults.life +DEF_NECTARD_ENERGY),spwanEggs(adults))
    case _ => Set()
  }

  def spwanEggs(adults: ButterflyImpl): EggsImpl =  adults match {
    case adults :ButterflyImpl => EggsImpl(name = adults.name + "-son"+Counter.nextValue, boundingBox = Circle(adults.boundingBox.point, randomValueChange(DEF_BLOB_RADIUS).max(MIN_BLOB_RADIUS)),
      fieldOfViewRadius = randomValueChange(DEF_BLOB_FOV_RADIUS).max(MIN_BLOB_FOV_RADIUS))
      case _ =>  ???
  }

//to eat and augment life     ---->FOODS   I Fondamental 1
  def iscollidedWithSimplePlant[A <:Butterfly](butterfly: Butterfly):Set[Butterfly]= butterfly match {
    case butterfly : EggsImpl =>Set(butterfly.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case butterfly : PuppaImpl =>Set(butterfly.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case butterfly : LarvaImpl =>Set(butterfly.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case butterfly : ButterflyImpl =>Set(butterfly.copy(life=butterfly.life+DEF_FOOD_ENERGY))
    case _ => Set()
  }

  def iscollidedWithPredactor[A <:Butterfly](eggs: A) :Set[Butterfly] = eggs  match{
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

  // def toBloom(butterfly: Butterfly): Set[Butterfly] = ???
  //def plantFoodEffect(eggs: CreatureImpl.EggsImpl) = ???
}






