package model

import model.common.BoundingBox.{Circle, Rectangle, Triangle}
import model.common.Direction
import model.common.Final.{DEF_BLOB_VELOCITY, DEF_BUTTERFLY_LIFE, DEF_LARVA_LIFE, DEF_NEXT_DIRECTION, DEF_PUPPA_LIFE}
import model.creature.Behavior.{EggsBehavior, LarvaBehavior, LeavesBehavior, NectarPlantBehavior, PlantBehavior, PredatorBehavior, PuppaImplBehavior, butterflyBehavior}
import model.creature.CreatureObject.TypeUtilities.{Degeneration, Increase, LifeCycle}
import model.creature.CreatureObject.{Butterfly, Plant, Predator}
import model.reaction.DegenerationE
import utils.TypeUtilities.{Collision, Life, MovementStrategy, SimulableEntity, Velocity}



object SimulationObjectImpl {



  case class EggsImpl(override val name: String,
                      override val  boundingBox: Circle,
                      override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION),
                      override val velocity: Velocity=DEF_BLOB_VELOCITY,
                      override val life: Life,//=DEF_EGG_LIFE,
                      override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                      override val movementStrategy: MovementStrategy,
                      override val lifeCycle: LifeCycle,// = 300 ,//= DegenerationE.setLifeCycle(),
                      override val changeStage: Increase[Butterfly] = DegenerationE.inc
  )extends Butterfly with EggsBehavior {

  }


  case class LeavesOfPlants(override val name: String,
                            override val boundingBox: Rectangle,
                            override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                            override val life: Life,
                            override val  collisionEffect: Collision,
                            val valor: Set[SimulableEntity],
                            override val  lifeCycle: LifeCycle// = updateLifeCyclecreatureOb
                           )extends Plant  with LeavesBehavior {




  }

  case class LarvaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_LARVA_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy,
                       //override val changeStage: Unit = ???
                       override val lifeCycle: LifeCycle,//= DegenerationE.setLifeCycle(),
                       override val  changeStage: Increase[Butterfly] = DegenerationE.inc
                      )extends Butterfly with LarvaBehavior {

  }


  case class PuppaImpl(override val name: String,
                       override val  boundingBox: Circle,
                       override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                       override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                       override val life: Life=DEF_PUPPA_LIFE,
                       override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                       override val movementStrategy: MovementStrategy ,
                       override val  lifeCycle: LifeCycle,//= DegenerationE.setLifeCycle(),
                       override val  changeStage: Increase[Butterfly] = DegenerationE.inc
                       //override val changeStage: Unit = ???
                      )extends Butterfly with  PuppaImplBehavior


  case class ButterflyImpl(override val name: String,
                           override val  boundingBox: Circle,
                           override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                           override val velocity: Velocity,//=DEF_BLOB_VELOCITY ,
                           override val life: Life=DEF_BUTTERFLY_LIFE,
                           override val degradationEffect: Degeneration[Butterfly] = DegenerationE.deacreaseLifeEffect,
                           override val movementStrategy: MovementStrategy,
                           override val  lifeCycle: LifeCycle,//= DegenerationE.setLifeCycle(),
                           override val  changeStage: Increase[Butterfly] = DegenerationE.inc
                          // override val changeStage: Unit = ???
                          )extends Butterfly with butterflyBehavior


  case class FlourPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant],
                        override val life: Life,
                        override val  collisionEffect: Collision,
                        override val  lifeCycle: LifeCycle// = ???
                       // override val  lifeCycle: LifeCycle = ???
                       )extends Plant with PlantBehavior


  case class NectarPlant(override val name: String,
                        override val boundingBox: Triangle,
                        override val degradationEffect: Degeneration[Plant] = DegenerationE.deacreaseLifeEffect,
                        override val life: Life,//=DEF_BUTTERFLY_LIFE,
                        override val  collisionEffect: Collision,
                         override val  lifeCycle: LifeCycle// = ???
                         //override val  lifeCycle: LifeCycle = ???
                        )extends Plant with NectarPlantBehavior

  case class PredatorImpl(override val name: String,
                          override val boundingBox: Rectangle,
                          override val degradationEffect: Degeneration[Predator] = DegenerationE.deacreaseLifeEffect ,
                          override val life: Life,
                          override val  collisionEffect: Collision,
                          override val  lifeCycle: LifeCycle,
                          override val direction: Direction = Direction(DEF_NEXT_DIRECTION,DEF_NEXT_DIRECTION) ,
                          override val velocity: Velocity,
                          override val movementStrategy: MovementStrategy,
                          )extends Predator with PredatorBehavior {

  }

}

