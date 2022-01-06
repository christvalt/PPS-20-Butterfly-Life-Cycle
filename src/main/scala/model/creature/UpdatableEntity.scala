package model.creature

import model.World
import model.creature.Behavior.{Simulable, SimulableEntity}
import model.creature.CreatureObject.Creature


/** a trait that defines the ability of update state, i.e. how a type extending this trait reacts when
 * calls updateState on it, once per iteration.
 */

trait UpdatableEntity{

  /**
   * Difines how a entity react in response to an updateSate notfication .
   * params:the World at the current iteration of the simulation
   * Returns: A set of simulableEntity
   * */
  def updateState(world:World): Set[SimulableEntity]
}



/**
 * compagnon object of UpdatableEntity
 * */
object UpdatableEntity{

  trait NeutralUpdatable  extends Simulable{
    self:Creature with Collidable=>
    override def updateState(world: World):Set[SimulableEntity]= Set(self)
  }


}



