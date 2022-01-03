package model

import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.Direction
import model.common.Final.{DEF_BLOB_VELOCITY, DEF_BUTTERFLY_LIFE, DEF_LARVA_LIFE, DEF_NEXT_DIRECTION, DEF_PUPPA_LIFE}
import model.creature.Behavior.{EggsBehavior, LarvaBehavior, LeavesBehavior, NectarPlantBehavior, PlantBehavior, PredatorBehavior, PuppaBehavior, butterflyBehavior}
import model.creature.CreatureObject.TypeUtilities.{Degeneration, Increase, LifeCycle}
import model.creature.CreatureObject.{Butterfly, Plant, Predator}
import model.reaction.DegenerationE
import utils.TypeUtilities.{Collision, Life, MovementStrategy, SimulableEntity, Velocity}


object SimulationObjectImpl {

  /**
   * Representation of a basic simulation object entity  which give us [Butterfly] with [EggsBehavior].
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param direction              entity direction.
   * @param velocity               entity velocity.
   * @param life                   entity life
   * @param degradationEffect      entity degeneration Effect.
   * @param movementStrategy       entity movement Strategy.
   * @param lifeCycle              entity lifeCycle.
   * @param changeStage            entity changeStage.

   */

  case class EggsImpl(override val name: String,
                      override val boundingBox: Circle,
                      override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION),
                      override val velocity: Velocity=DEF_BLOB_VELOCITY,
                      override val life: Life,//=DEF_EGG_LIFE,
                      override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                      override val movementStrategy: MovementStrategy,
                      override val lifeCycle: LifeCycle,
                      override val changeStage: Increase[Butterfly] = DegenerationE.inc
  )extends Butterfly with EggsBehavior
  /**
   * Representation of a basic simulation object entity which give us [Plant] with [LeavesBehavior] .
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param degradationEffect      entity degeneration Effect.
   * @param life                   entity life
   * @param collisionEffect        entity collision.
   * @param lifeCycle              entity lifeCycle.

   */

  case class LeavesOfPlants(override val name: String,
                            override val boundingBox: Rectangle,
                            override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                            override val life: Life,
                            override val collisionEffect: Collision,
                            override val  lifeCycle: LifeCycle
                           )extends Plant  with LeavesBehavior

  /**
   *
   * Representation of a basic simulation object entity  which give us [Butterfly] with [LarvaBehavior] .
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param direction              entity direction.
   * @param velocity               entity velocity.
   * @param life                   entity life
   * @param degradationEffect      entity degeneration Effect.
   * @param movementStrategy       entity movement Strategy.
   * @param lifeCycle              entity lifeCycle.
   * @param changeStage            entity changeStage.
   */



  case class LarvaImpl(override val name: String,
                       override val boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,
                       override val life: Life=DEF_LARVA_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy,
                       override val lifeCycle: LifeCycle,
                       override val changeStage: Increase[Butterfly] = DegenerationE.inc
                      )extends Butterfly with LarvaBehavior
  /**
   * Representation of a basic simulation object entity which give us [Butterfly] with [PuppaBehavior].
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param direction              entity direction.
   * @param velocity               entity velocity.
   * @param life                   entity life
   * @param degradationEffect      entity degeneration Effect.
   * @param movementStrategy       entity movement Strategy.
   * @param lifeCycle              entity lifeCycle.
   * @param changeStage            entity changeStage.
   */

  case class PuppaImpl(override val name: String,
                       override val boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,
                       override val life: Life=DEF_PUPPA_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy ,
                       override val lifeCycle: LifeCycle,
                       override val changeStage: Increase[Butterfly] = DegenerationE.inc
                      )extends Butterfly with  PuppaBehavior


  /**
   * Representation of a basic simulation object entity which give us [Butterfly] with [butterflyBehavior] .
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param direction              entity direction.
   * @param velocity               entity velocity.
   * @param life                   entity life
   * @param degradationEffect      entity degeneration Effect.
   * @param movementStrategy       entity movement Strategy.
   * @param lifeCycle              entity lifeCycle.
   * @param changeStage            entity changeStage.

   */
  case class ButterflyImpl(override val name: String,
                           override val boundingBox: Circle,
                           override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                           override val velocity: Velocity,
                           override val life: Life=DEF_BUTTERFLY_LIFE,
                           override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                           override val movementStrategy: MovementStrategy,
                           override val lifeCycle: LifeCycle,
                           override val changeStage: Increase[Butterfly] = DegenerationE.inc
                          )extends Butterfly with butterflyBehavior

  /**
   * Representation of a basic simulation object entity which give us [Plant] with [PlantBehavior].
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param degradationEffect      entity degeneration Effect.
   * @param life                   entity life
   * @param collisionEffect        entity collision.
   * @param lifeCycle              entity lifeCycle.

   */

  case class FlowerPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant],
                        override val life: Life,
                        override val collisionEffect: Collision,
                        override val lifeCycle: LifeCycle
                       )extends Plant with PlantBehavior

  /**
   * Representation of a basic simulation object entity which give us [Plant] with [NectarPlantBehavior].
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param degradationEffect      entity degeneration Effect.
   * @param life                   entity life.
   * @param collisionEffect        entity collision.
   * @param lifeCycle              entity lifeCycle.

   */

  case class NectarPlant(override val name: String,
                        override val   boundingBox: Triangle,
                        override val   degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                        override val   life: Life,
                        override val   collisionEffect: Collision,
                         override val  lifeCycle: LifeCycle
                        )extends Plant with NectarPlantBehavior

  /**
   * Representation of a basic simulation object entity which give us [Predator] with [PredatorBehavior] .
   * @param name                   entity name.
   * @param boundingBox            entity boundingBox.
   * @param degradationEffect      entity degeneration Effect.
   * @param life                   entity life.
   * @param collisionEffect        entity collision.
   * @param lifeCycle              entity lifeCycle.
   * @param direction              entity direction.
   * @param velocity               entity velocity.
   * @param movementStrategy       entity movement Strategy.

   */

  case class PredatorImpl(override val name: String,
                          override val boundingBox: Rectangle,
                          override val degradationEffect: Degeneration[Predator] = DegenerationE.deacreaseLifeEffect ,
                          override val life: Life,
                          override val collisionEffect: Collision,
                          override val lifeCycle: LifeCycle,
                          override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                          override val velocity: Velocity,
                          override val movementStrategy: MovementStrategy,
                          )extends Predator with PredatorBehavior {

  }

}

