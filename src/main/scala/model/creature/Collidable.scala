package model.creature

import model.creature.Behavior.{Simulable, SimulableEntity}
import model.creature.CreatureObject.Creature

/** a trait that defines the ability of update state, i.e. how a type extending this trait reacts when
 * calls updateState on it, once per iteration.
 */
trait  Collidable{
  /**
   * Difines how a entity react in response to an collision notfication .
   * params:the World at the current iteration of the simulation
   * Returns: A set of simulableEntity
   * */
  def collision(other:SimulableEntity):Set [SimulableEntity]
}
/**collidable interface with it's compagnon
 *  object  tht represent the creature that
 *  collide in the system*/

object Collidable {
  trait NeutralCollidable extends Simulable {
    self: Creature  =>
    override def collision(other: SimulableEntity): Set[SimulableEntity] = Set(self)
  }

}
