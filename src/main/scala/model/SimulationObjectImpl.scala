package model

import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.{BoundingBox, Direction}
import model.creature.Behavior.{EggsBehavior, LarvaBehavior, NectarPlantBehavior, PlantBehavior, PredatorBehavior, PuppaImplBehavior, butterflyBehavior}
import model.creature.CreatureObject.Domain.{ChangingStage, Collision, Degeneration, Increase, Life, LifeCycle, MovementStrategy, Velocity}
import model.creature.CreatureObject.{Butterfly, Living, Plant, Predator}
import model.reaction.DegenerationE
import model.reaction.DegenerationE.{STANDARD_LIFE_DECREASE}

import java.sql.RowIdLifetime


object SimulationObjectImpl {
  val DEF_BUTTERFLY_LIFE = 1250
  val DEF_EGG_LIFE = 50
  val DEF_PUPPA_LIFE = 300
  val DEF_LARVA_LIFE = 50
  val DEF_BLOB_VELOCITY = 50
  val DEF_BLOB_FOV_RADIUS = 50
  val DEF_NEXT_DIRECTION = 0
  val DEF_DAY_FOR_HIBERNATION_EGGS=500

  case class EggsImpl(override val name: String,
                      override val  boundingBox: Circle,
                      override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION),
                      override val velocity: Velocity=DEF_BLOB_VELOCITY,
                      override val life: Life=DEF_EGG_LIFE,
                      override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                      override val movementStrategy: MovementStrategy,
                      //override val  lifeCycle: LifeCycle// = updateLifeCyclecreatureOb
  )extends Butterfly with EggsBehavior {
  }

  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_LARVA_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy,
                       //override val changeStage: Unit = ???
                       //override val  lifeCycle: LifeCycle// = ???
                      )extends Butterfly with LarvaBehavior {

  }


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_PUPPA_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy ,
                      // override val  lifeCycle: LifeCycle// = ???
                       //override val changeStage: Unit = ???
                      )extends Butterfly with  PuppaImplBehavior


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                           override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BUTTERFLY_LIFE,
                           override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                           override val movementStrategy: MovementStrategy,
                           //override val  lifeCycle: LifeCycle// = ???
                          // override val changeStage: Unit = ???
                          )extends Butterfly with butterflyBehavior


  case class FlourPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant],
                        override val life: Life,
                        override val  collisionEffect: Collision,
                       // override val  lifeCycle: LifeCycle// = ???
                       // override val  lifeCycle: LifeCycle = ???
                       )extends Plant with PlantBehavior


  case class NectarPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                        override val life: Life,//=DEF_BUTTERFLY_LIFE,
                        override val  collisionEffect: Collision,
                        // override val  lifeCycle: LifeCycle// = ???
                         //override val  lifeCycle: LifeCycle = ???
                        )extends Plant with NectarPlantBehavior

  case class PredatorImpl(override val name: String,
                          override val boundingBox: Rectangle,
                          override val degradationEffect: Degeneration[Predator] ,
                          override val life: Life,//=DEF_BUTTERFLY_LIFE,
                          override val  collisionEffect: Collision,
                         // override val  lifeCycle: LifeCycle// = ???
                          //override val  lifeCycle: LifeCycle = ???
                          )extends Predator with PredatorBehavior

}