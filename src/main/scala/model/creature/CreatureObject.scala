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


  trait Creature{
    val name : String
    def boundingBox: BoundingBox
  }


  sealed trait Living extends Creature {
    def life: Life
    def lifeCycle: LifeCycle

  }

  sealed trait Moving extends Creature {
    def velocity: Velocity
  }
  //volare
  sealed trait fliying extends Creature {
    def velocity: Velocity
  }

  // trait that represent entity that react to an evenmemt in the environemt
  sealed trait eating extends Creature {
    def collisionEffect: Collision
  }


  sealed trait Intelligent extends Creature with Moving {
    def movementStrategy: MovementStrategy
    def direction: Direction
   // def changeStage : ChangingStage
  }

  trait Butterfly extends Creature with Living with Moving with Intelligent {
    override def boundingBox: BoundingBox
    def degradationEffect: Degeneration[Butterfly]
    def changeStage: Increase[Butterfly]
  }

  trait ButterflyWithTemporaryStatus extends Butterfly {
    def tochange: ToChange
  }

//trait Predator extends Creature with Living with Moving with eating {
//    override def boundingBox: Rectangle
//  }

  trait Predator extends Creature with Living  with eating with Intelligent {
    override def boundingBox: Rectangle
    def degradationEffect: Degeneration[Predator]
  }

  trait Plant extends Creature with Living  with eating{
    override def boundingBox: BoundingBox
    def degradationEffect: Degeneration[Plant]
  }

/*
*   trait Plant extends Creature {
    override def boundingBox: Rectangle
    def lifeCycle: LifeCycle
  }*/






}


