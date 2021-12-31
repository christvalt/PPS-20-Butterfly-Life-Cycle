package model.reaction

import model.SimulationObjectImpl.{EggsImpl, LarvaImpl, PuppaImpl}
import model.common.BoundingBox.Circle
import model.common.Final.{DEF_PUPPA_LIFE, STANDARD_LIFE_DECREASE, STANDARD_LIFE_INCREASE}
import model.common.Point2D
import model.creature.CreatureObject.TypeUtilities.LifeCycle
import model.creature.CreatureObject.Living
import utils.TypeUtilities.{Life, SimulableEntity}




object DegenerationE  {



  def deacreaseLifeEffect(creatureOb: Living): Life = creatureOb.life - STANDARD_LIFE_DECREASE


  def setLifeCycle(lifeCycle: Int):Int = lifeCycle + 1

  def inc (creatureOb: Living): LifeCycle = creatureOb.lifeCycle + STANDARD_LIFE_INCREASE

  //var incrr: EggsImpl => Life = (egg: => EggsImpl) => egg.lifeCycle+STANDARD_LIFE_INCREASE



  trait  testing  extends EggsImpl {
    self: EggsImpl =>

    def inc = () => self.lifeCycle+STANDARD_LIFE_INCREASE

  }



  def helperEggToLarva[A<: EggsImpl](egg:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    //println("cycle" + egg.lifeCycle)
    LarvaImpl(egg.name +"new larva" ,
      egg.boundingBox,
      egg.direction,
      egg.velocity+15,
      egg.life -1 ,
      egg.degradationEffect ,
      egg.movementStrategy,
      lifeCycle=8,
      egg.changeStage)
  }


  def helperLarvaToPuppa[A<: PuppaImpl](puppa:A): SimulableEntity ={
    //println("cycleTEst" + puppa.lifeCycle)
    PuppaImpl(puppa.name +"new Puppa" ,
      boundingBox = Circle(point =  Point2D(444, 355),radius = 10),
      puppa.direction,
      puppa.velocity+35,
      puppa.life  ,
      puppa.degradationEffect ,
      puppa.movementStrategy,
      puppa.lifeCycle ,// =egg.changeStage(egg),
      puppa.changeStage)




  }

  def helperLarvaToAdult[A<: LarvaImpl](larva:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    PuppaImpl(larva.name +"new Puppa" ,
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
    PuppaImpl(egg.name +"new Puppa" ,
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



