package model.reaction

import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl, flourPlant}
import model.creature.CreatureObject.{Creature}
import model.creature.CreatureObject.Domain.Life

object BeingEatenEffect {
  val REDUCE_LIFE = 250
  val SPECIAL_REDUCATION_FOR_EGGS= 500

  def eatingByPredatorEffect(creture: Creature): Life = creture match {
    case creture: EggsImpl =>  creture.life - SPECIAL_REDUCATION_FOR_EGGS
    case creture: PuppaImpl =>creture.life - REDUCE_LIFE
    case creture :LarvaImpl=>creture.life - REDUCE_LIFE
    case creture :ButterflyImpl=>creture.life - REDUCE_LIFE
    case creture :flourPlant=>creture.life - REDUCE_LIFE
    case creture:flourPlant=>creture.life - REDUCE_LIFE
  }

}
