package model

import model.BoundingBox.{Circle, Rectangle, Triangle}
import model.creature.Behavior.{EggsBehavior, EggsBehavior2LarvaBehavior, LarvaBehavior, NectarPlantBehavior, PlantBehavior, PredatorBehavior, PuppaImplBehavior, SimulableEntity, butterflyBehavior}
import model.creature.{CreatureObject, Direction, MovingStrategies}
import model.creature.CreatureObject.Domain.{Collision, Degeneration, Life, MovementStrategy, Velocity}
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}
import model.reaction.DegenerationE


object SimulationObjectImpl {
  val DEF_BLOB_LIFE = 1250
  val DEF_BLOB_VELOCITY = 50
  val DEF_BLOB_FOV_RADIUS = 50
  val DEF_NEXT_DIRECTION = 0

  case class EggsImpl(override val name: String,
                      override val  boundingBox: Circle,
                      override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                      override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                      override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                      override val life: Life=DEF_BLOB_LIFE,
                      override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                      override val movementStrategy: MovementStrategy = MovingStrategies.baseMovement,
                     )extends Butterfly with EggsBehavior

  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy = MovingStrategies.baseMovement,
                      )extends Butterfly with LarvaBehavior


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy = MovingStrategies.baseMovement,
                      )extends Butterfly with  PuppaImplBehavior


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                           override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                           override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BLOB_LIFE,
                           override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                           override val movementStrategy: MovementStrategy = MovingStrategies.baseMovement,
                          )extends Butterfly with butterflyBehavior


  case class flourPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant],
                        override val life: Life,
                        override val  collisionEffect: Collision)extends Plant with PlantBehavior


  case class NectarPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                        override val life: Life=DEF_BLOB_LIFE,
                        override val  collisionEffect: Collision)extends Plant with NectarPlantBehavior

  case class PredatorImpl(override val name: String,
                          override val boundingBox: Rectangle,
                          override val degradationEffect: Degeneration[Predator] ,
                          override val life: Life=DEF_BLOB_LIFE,
                          override val  collisionEffect: Collision
                          )extends Predator with PredatorBehavior
}

///config final


