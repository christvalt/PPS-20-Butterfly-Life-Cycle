package model.creature

import model.creature.CreatureObject.TypeUtilities.{ Degeneration, Increase,  LifeCycle}
import model.World
import model.common.{BoundingBox, Direction, Movement}
import model.common.BoundingBox.{Rectangle, Triangle}
import model.creature.Behavior.SimulableEntity
import utils.TypeUtilities.{Collision, Life, MovementStrategy, ToChange, Velocity}


object CreatureObject {


  object TypeUtilities {
    type LifeCycle = Int
    type Increase[A] = A => Life
    type Degeneration[A] = A => Life

  }

  /**
   * trait that  represent a standard Creature in the simulation.
   */
  trait Creature{
    val name : String
    def boundingBox: BoundingBox
  }

/** trait that represent an Creature in the simulation that can live or die.*/
  sealed trait Living extends Creature {
    def life: Life
    def lifeCycle: LifeCycle

  }


  /**trait represent an entity in the simulation that can move in the world boundaries*/
  sealed trait Moving extends Creature {
    def velocity: Velocity
  }
  sealed trait fliying extends Creature {
    def velocity: Velocity
  }

  /** Trait that represent creature that react to an evenmemt in the environemt*/
  sealed trait eating extends Creature {
    def collisionEffect: Collision
  }

/**This trait represent an creature in the simulation that has the ability move in different directions*/
  sealed trait Intelligent extends Creature with Moving {
    def movementStrategy: MovementStrategy
    def direction: Direction
  }

  trait Butterfly extends Creature with Living with Moving with Intelligent {
    override def boundingBox: BoundingBox
    def degradationEffect: Degeneration[Butterfly]
    def changeStage: Increase[Butterfly]
  }

/**This trait represent a Predator abstraction entity.*/
  trait Predator extends Creature with Living  with eating with Intelligent {
    override def boundingBox: Rectangle
    def degradationEffect: Degeneration[Predator]
  }

  /**This trait represent a Food abstraction entity.*/
  trait Plant extends Creature with Living  with eating{
    override def boundingBox: BoundingBox
    def degradationEffect: Degeneration[Plant]
  }
}


