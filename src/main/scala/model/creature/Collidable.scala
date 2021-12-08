package model.creature

import model.creature.Behavior.{Simulable, SimulableEntity}
import model.creature.CreatureObject.Creature

//collidable interface with it's compagnio object  tht represent the creature that collide in the system
trait  Collidable{

  def collision(other:SimulableEntity):Set [SimulableEntity]
}

object Collidable {
  trait NeutralCollidable extends Simulable {
    self: Creature  =>
    override def collision(other: SimulableEntity): Set[SimulableEntity] = Set(self)
  }

}
