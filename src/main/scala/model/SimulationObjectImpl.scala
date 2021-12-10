package model

import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.Direction
import model.creature.Behavior.{EggsBehavior, LarvaBehavior, NectarPlantBehavior, PlantBehavior, PredatorBehavior, PuppaImplBehavior, butterflyBehavior}
import model.creature.CreatureObject.Domain.{Collision, Degeneration, Life, MovementStrategy, Velocity}
import model.creature.CreatureObject.{Butterfly, Plant, Predator}
import model.reaction.DegenerationE


object SimulationObjectImpl {
  val DEF_BLOB_LIFE = 1250
  val DEF_BLOB_VELOCITY = 50
  val DEF_BLOB_FOV_RADIUS = 50
  val DEF_NEXT_DIRECTION = 0

  case class EggsImpl(override val name: String,
                      override val  boundingBox: Circle,
                      override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                      override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                      override val life: Life=DEF_BLOB_LIFE,
                      override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                      override val movementStrategy: MovementStrategy
                     )extends Butterfly with EggsBehavior

  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy ,
                      )extends Butterfly with LarvaBehavior


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy ,
                      )extends Butterfly with  PuppaImplBehavior


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                           override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BLOB_LIFE,
                           override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                           override val movementStrategy: MovementStrategy,
                          )extends Butterfly with butterflyBehavior


  case class FlourPlant(override val name: String,
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