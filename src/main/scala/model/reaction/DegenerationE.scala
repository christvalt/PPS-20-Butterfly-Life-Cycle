package model.reaction

import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, PuppaImpl}
import model.common.BoundingBox.Circle
import model.common.Final.{DEF_PUPPA_LIFE, LIFE_ADD_EGG_TO_LARVA, STANDARD_LIFE_DECREASE, STANDARD_LIFE_INCREASE, VELOCITY_ADD_EGG_TO_LARVA, VELOCITY_ADD_LARVA_TO_PUPPA}
import model.common.Point2D
import model.creature.CreatureObject.TypeUtilities.LifeCycle
import model.creature.CreatureObject.Living
import utils.TypeUtilities.{Life, SimulableEntity}




object DegenerationE  {



  def deacreaseLifeEffect(creatureOb: Living): Life = creatureOb.life - STANDARD_LIFE_DECREASE


  def deacreaseLifeEffectForLarva(creatureOb: Living): Life = creatureOb.life - STANDARD_LIFE_DECREASE+1

  def inc (creatureOb: Living): LifeCycle = creatureOb.lifeCycle + STANDARD_LIFE_INCREASE


  def helperEggToLarva[A<: EggsImpl](egg:A): SimulableEntity ={
    LarvaImpl(egg.name +"new larva" ,
      egg.boundingBox,
      egg.direction,
      egg.velocity+VELOCITY_ADD_EGG_TO_LARVA,
      egg.life + LIFE_ADD_EGG_TO_LARVA ,
      egg.degradationEffect ,
      egg.movementStrategy,
      egg.lifeCycle,
      egg.changeStage)
  }


  def helperLarvaToPuppa[A<: LarvaImpl](puppa:A): SimulableEntity ={
    PuppaImpl(puppa.name +"new Larva" ,
      puppa.boundingBox,
      puppa.direction,
      puppa.velocity+VELOCITY_ADD_LARVA_TO_PUPPA,
      puppa.life+ LIFE_ADD_EGG_TO_LARVA   ,
      puppa.degradationEffect ,
      puppa.movementStrategy,
      puppa.lifeCycle ,
      puppa.changeStage)
  }

  def helperPuppaToAdult[A<: PuppaImpl](larva:A): SimulableEntity ={
    println("puppa name"+ larva.life)
    ButterflyImpl(larva.name +"new Puppa" ,
      larva.boundingBox,
      larva.direction,
      larva.velocity+35,
      larva.life +DEF_PUPPA_LIFE ,
      larva.degradationEffect ,
      larva.movementStrategy,
      larva.lifeCycle,
      larva.changeStage)
  }

  def helperAdultSpwoonEggs[A<: EggsImpl](egg:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    PuppaImpl(egg.name +"new egg son" ,
      egg.boundingBox,
      egg.direction,
      egg.velocity+35,
      egg.life +DEF_PUPPA_LIFE ,
      egg.degradationEffect ,
      egg.movementStrategy,
      egg.lifeCycle,
      egg.changeStage)
  }

}



