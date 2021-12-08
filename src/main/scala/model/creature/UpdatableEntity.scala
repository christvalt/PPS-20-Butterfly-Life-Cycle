package model.creature

import model.World
import model.creature.Behavior.{Simulable, SimulableEntity}
import model.creature.CreatureObject.Creature




trait UpdatableEntity{
  def updateState(world:World): Set[SimulableEntity]
}

object UpdatableEntity{

  trait NeutralUpdatable  extends Simulable{
    self:Creature with Collidable=>
    override def updateState(world: World):Set[SimulableEntity]= Set(self)
  }


}



