package model.reaction

import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlowerPlant, LarvaImpl, PuppaImpl}
import model.creature.CreatureObject.Creature
import utils.TypeUtilities.Life

object BeingEatenEffect {
  val REDUCE_LIFE = 250
  val SPECIAL_REDUCATION_FOR_EGGS= 500

  def eatingByPredatorEffect(creture: Creature): Life = creture match {
    case creture: EggsImpl =>  creture.life - SPECIAL_REDUCATION_FOR_EGGS
    case creture: PuppaImpl =>creture.life - REDUCE_LIFE
    case creture :LarvaImpl=>creture.life - REDUCE_LIFE
    case creture :ButterflyImpl=>creture.life - REDUCE_LIFE
    case creture :FlowerPlant=>creture.life - REDUCE_LIFE
    case creture:FlowerPlant=>creture.life - REDUCE_LIFE
  }

}
