package model

import com.sun.javafx.scene.traversal.Direction
import model.BoundingBox.Circle
import model.creature.creatureStructure.Domain.{Life, Velocity}
import model.creature.creatureStructure.{Butterfly, Creature}

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
                     )extends Butterfly


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction:Int=DEF_NEXT_DIRECTION ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                      )extends Butterfly



  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction:Int=DEF_NEXT_DIRECTION ,
                       override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                       override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_BLOB_LIFE,
                      )extends Butterfly


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction:Int=DEF_NEXT_DIRECTION ,
                           override val fieldOfViewRadius: Int=DEF_BLOB_FOV_RADIUS,
                           override val velocity: Velocity=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BLOB_LIFE,
                          )extends Butterfly
}


