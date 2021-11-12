package model.creature


import model.creature.creatureStructure.Domain.{EatingEffect, DegradationEffect, Life, LifeCycle, ToChange, Velocity}
import model.BoundingBox
import model.BoundingBox.{Circle, Rectangle}

object creatureStructure {


  object Domain {
    type Life = Int
    type Velocity = Int
    //type Position = Movement
    type ToChange = Int
    type LifeCycle = Int
    type DegradationEffect[A] = A => Life
    type EatingEffect = Butterfly => Set[Creature]
    // type MovementStrategy = (Intelligent, World, Entity => Boolean) => Position
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
    def eatingEffect: EatingEffect
  }

  /*
  *
  * sealed trait Perceptive extends Creature {
    def fieldOfViewRadius : Int
  }*/

  //trait that represent entity that is inteligent so can see within a certain range (fieldOfViewRadius)
  //and can move with a selected direction

  sealed trait Intelligent extends Creature with Moving {
    //def movementStrategy: MovementStrategy
    def direction: Int//Direction
    def fieldOfViewRadius : Int
  }

  trait Butterfly extends Creature with Living with Moving with Intelligent {
    override def boundingBox: BoundingBox
    def degradationEffect: DegradationEffect[Butterfly]
  }

  trait ButterflyWithTemporaryStatus extends Butterfly {
    def tochange: ToChange
  }


  trait Food extends Creature with Living {
    override def boundingBox: Circle
    def degradationEffect: DegradationEffect[Food]
  }


  trait Predator extends Creature{
    override def boundingBox: Rectangle
  }

  trait Plant extends Creature with Living  {
    override def boundingBox: Circle
    def degradationEffect: DegradationEffect[Plant]
  }

/*
*   trait Plant extends Creature {
    override def boundingBox: Rectangle
    def lifeCycle: LifeCycle
  }*/






}
