package model.reaction

import model.creature.creatureStructure.Domain.Life
import model.creature.creatureStructure.Living




object DegenerationE  {

  val STANDARD_LIFE_DECREASE = 10

  def deacreaseLifeEffect(creatureOb: Living): Life = creatureOb.life - STANDARD_LIFE_DECREASE
}


