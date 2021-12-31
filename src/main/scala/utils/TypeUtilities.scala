package utils

import model.World
import model.common.Movement
import model.creature.Behavior.{Simulable}
import model.creature.CreatureObject.{Butterfly, Creature, Intelligent}

object TypeUtilities {

    type Life = Int
    type Velocity = Int
    type Position = Movement
    type ToChange = Int
    type Collision = Butterfly => Set[SimulableEntity]
    type MovementStrategy = (Intelligent, World) => Position
    type ChangingStage= (Intelligent ,World)=> Creature
    type SimulableEntity = Creature with Simulable
}
