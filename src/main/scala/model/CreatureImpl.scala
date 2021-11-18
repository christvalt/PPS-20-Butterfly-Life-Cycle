package model

import model.BoundingBox.{Circle, Triangle}
import model.creature.Behavior.{EggsBehavior, LarvaBehavior, NectarPlantBehavior, PlantBehavior, PredatorBehavior, PuppaImplBehavior, butterflyBehavior}
import model.creature.creatureStructure
import model.creature.creatureStructure.Domain.{Collision, Degeneration, Life, Velocity}
import model.creature.creatureStructure.{Butterfly, Creature, Plant, Predator}
import model.reaction.DegenerationE


object CreatureImpl {
  val DEF_BLOB_LIFE = 1250
  val DEF_BLOB_VELOCITY = 50
  val DEF_BLOB_FOV_RADIUS = 50
  val DEF_NEXT_DIRECTION = 0

  case class EggsImpl(override val name: String,
                      override val  boundingBox: Circle,
                      override val direction:Int=DEF_NEXT_DIRECTION ,
                      override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                      override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                      override val life: Life=DEF_BLOB_LIFE,
                      override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect
                     )extends Butterfly with EggsBehavior


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction:Int=DEF_NEXT_DIRECTION ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] =  DegenerationE.deacreaseLifeEffect
                      )extends Butterfly with  PuppaImplBehavior



  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction:Int=DEF_NEXT_DIRECTION ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] =  DegenerationE.deacreaseLifeEffect
                      )extends Butterfly with LarvaBehavior


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction:Int=DEF_NEXT_DIRECTION ,
                           override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                           override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BLOB_LIFE,
                           override val degradationEffect: Degeneration[Butterfly] =  DegenerationE.deacreaseLifeEffect
                          )extends Butterfly with butterflyBehavior


  case class flourPlant(override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                        override val life: Life=DEF_BLOB_LIFE,
                        override val name: String,
                        override val  collisionEffect: Collision)extends Plant with PlantBehavior {
  }

  case class NectarPlant(override val boundingBox: Triangle,
                         override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                         override val life: Life=DEF_BLOB_LIFE,
                         override val name: String,
                         override val  collisionEffect: Collision)extends Plant with NectarPlantBehavior{

  }

  case class PredatorImpl( override val name: String,
                           override val boundingBox: Circle,
                           override val life: Life=DEF_BLOB_LIFE,
                           override val velocity: Velocity =DEF_BLOB_VELOCITY,
                           override val collisionEffect: Collision
                          )extends Predator with PredatorBehavior {

  }
}

///config final


