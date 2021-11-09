package model.reaction

import model.CreatureImpl
import model.CreatureImpl.EggsImpl
import model.creature.creatureStructure.Butterfly

import scala.Byte.MaxValue

object reaction {

  val DEF_FOOD_ENERGY = 222

  def collisionbetweennButterflyAndPLan(eggs:EggsImpl):Set[Butterfly] = eggs  match {
    case eggs =>Set(eggs.copy(life=eggs.life+DEF_FOOD_ENERGY))
    case _ => Set()

  }


  def plantFoodEffect(eggs: CreatureImpl.EggsImpl) = ???

}




