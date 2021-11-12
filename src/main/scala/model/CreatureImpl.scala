package model

import model.BoundingBox.{Circle, Triangle}
import model.creature.creatureStructure
import model.creature.creatureStructure.Domain.{DegradationEffect, Life, Velocity}
import model.creature.creatureStructure.{Butterfly, Creature, Plant}

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
                      override val degradationEffect: DegradationEffect[Butterfly] = ???
                     )extends Butterfly {

  }


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction:Int=DEF_NEXT_DIRECTION ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: DegradationEffect[Butterfly] = ???
                      )extends Butterfly



  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction:Int=DEF_NEXT_DIRECTION ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                       override val degradationEffect: DegradationEffect[Butterfly] = ???
                      )extends Butterfly


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction:Int=DEF_NEXT_DIRECTION ,
                           override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                           override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BLOB_LIFE,
                           override val degradationEffect: DegradationEffect[Butterfly] = ???
                          )extends Butterfly


  case class flourPlant(override val boundingBox: Circle,
                        override val degradationEffect: DegradationEffect[Plant] = ???,
                        override val life: Life=DEF_BLOB_LIFE,
                        override val name: String )extends Plant {
  }


  case class NectarPlant(override val boundingBox: Circle,
                        override val degradationEffect: DegradationEffect[Plant] = ???,
                        override val life: Life=DEF_BLOB_LIFE,
                        override val name: String )extends Plant {
  }

}

///config final


