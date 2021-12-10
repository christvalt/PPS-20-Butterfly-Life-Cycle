package model.creature

import model.creature.CreatureObject.Domain.{Collision, Degeneration, Life, MovementStrategy, ToChange, Velocity}
import model.World
import model.common.{BoundingBox, Direction, Movement}
import model.common.BoundingBox.{Rectangle, Triangle}
import model.creature.Behavior.SimulableEntity


object CreatureObject {


  object Domain {
    type Life = Int
    type Velocity = Int
    type Position = Movement
    type ToChange = Int
    type LifeCycle = Int
    type Degeneration[A] = A => Life
    type Collision = Butterfly => Set[SimulableEntity]
    type MovementStrategy = (Intelligent, World) => Position
  }


  trait Creature{
    val name : String
    def boundingBox: BoundingBox
  }


  sealed trait Living extends Creature {
    def life: Life
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
   // def stage : BoundingBox
  }

  trait Butterfly extends Creature with Living with Moving with Intelligent {
    override def boundingBox: BoundingBox
    def degradationEffect: Degeneration[Butterfly]
    //override  def stage:BoundingBox
  }

  trait ButterflyWithTemporaryStatus extends Butterfly {
    def tochange: ToChange
  }

//trait Predator extends Creature with Living with Moving with eating {
//    override def boundingBox: Rectangle
//  }

  trait Predator extends Creature with Living  with eating{
    override def boundingBox: Rectangle
    def degradationEffect: Degeneration[Predator]
  }

  trait Plant extends Creature with Living  with eating{
    override def boundingBox: Triangle
    def degradationEffect: Degeneration[Plant]
  }

/*
*   trait Plant extends Creature {
    override def boundingBox: Rectangle
    def lifeCycle: LifeCycle
  }*/






}


