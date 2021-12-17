package model.reaction

import model.SimulationObjectImpl.{DEF_PUPPA_LIFE, EggsImpl, LarvaImpl, PuppaImpl}
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.Domain.Life
import model.creature.CreatureObject.{Butterfly, Living}




object DegenerationE  {

  val STANDARD_LIFE_DECREASE = 0
  val STANDARD_LIFE_INCREASE = 1

  def deacreaseLifeEffect(creatureOb: Living): Life = creatureOb.life - STANDARD_LIFE_DECREASE








 // val lifeUpfa = (i:EggsImpl)=>i.copy(lifeCycle=i.lifeCycle+1)






  //what would be changed
  //life
  //velocity
  //movement
  //integral type so to eggIMpl to ButteflyImpl


  def helperEggToPuppa[A<: EggsImpl](egg:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    PuppaImpl(egg.name +"new Puppa" ,
     egg.boundingBox,
      egg.direction,
      egg.velocity+35,
      egg.life +DEF_PUPPA_LIFE ,
      egg.degradationEffect ,
      egg.movementStrategy)
  }


  def helperPuppaToLarva[A<: PuppaImpl](puppa:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    LarvaImpl(puppa.name +"new Puppa" ,
      puppa.boundingBox,
      puppa.direction,
      puppa.velocity+35,
      puppa.life +DEF_PUPPA_LIFE ,
      puppa.degradationEffect ,
      puppa.movementStrategy)
  }

  def helperLarvaToAdult[A<: LarvaImpl](larva:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    PuppaImpl(larva.name +"new Puppa" ,
      larva.boundingBox,
      larva.direction,
      larva.velocity+35,
      larva.life +DEF_PUPPA_LIFE ,
      larva.degradationEffect ,
      larva.movementStrategy)
  }

  def helperAdultSpwoonEggs[A<: EggsImpl](egg:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    PuppaImpl(egg.name +"new Puppa" ,
      egg.boundingBox,
      egg.direction,
      egg.velocity+35,
      egg.life +DEF_PUPPA_LIFE ,
      egg.degradationEffect ,
      egg.movementStrategy)
  }





}



