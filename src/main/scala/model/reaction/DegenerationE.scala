package model.reaction

import model.creature.CreatureObject.Domain.Life
import model.creature.CreatureObject.Living




object DegenerationE  {

  val STANDARD_LIFE_DECREASE = 5

  def deacreaseLifeEffect(creatureOb: Living): Life = creatureOb.life - STANDARD_LIFE_DECREASE
}


