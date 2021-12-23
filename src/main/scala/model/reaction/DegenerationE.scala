package model.reaction

import model.SimulationObjectImpl.{DEF_PUPPA_LIFE, EggsImpl, LarvaImpl, PuppaImpl}
import model.World
import model.common.BoundingBox.Circle
import model.common.Point2D
import model.creature.CreatureObject.TypeUtilities.LifeCycle
import model.creature.CreatureObject.{Butterfly, Living}
import utils.TypeUtilities.{Life, SimulableEntity}




object DegenerationE  {

  val STANDARD_LIFE_DECREASE = 0.5
  val STANDARD_LIFE_INCREASE = 1

  def deacreaseLifeEffect(creatureOb: Living): Life = creatureOb.life - 2


  def setLifeCycle(lifeCycle: Int):Int = lifeCycle +1

  def inc (creatureOb: Living): LifeCycle = creatureOb.lifeCycle + STANDARD_LIFE_INCREASE

  //var incrr: EggsImpl => Life = (egg: => EggsImpl) => egg.lifeCycle+STANDARD_LIFE_INCREASE



  trait  testing  extends EggsImpl {
    self: EggsImpl =>

    def inc = () => self.lifeCycle+STANDARD_LIFE_INCREASE

  }



  def helperEggToPuppa[A<: EggsImpl](egg:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    //println("cycle" + egg.lifeCycle)
    PuppaImpl(egg.name +"new Puppa" ,
      boundingBox = Circle(point =  Point2D(444, 355),radius = 10),
      egg.direction,
      egg.velocity+35,
      egg.life  ,
      egg.degradationEffect ,
      egg.movementStrategy,
      egg.lifeCycle ,// =egg.changeStage(egg),
      egg.changeStage)
  }


  def helperPuppaToLarva[A<: PuppaImpl](puppa:A): SimulableEntity ={
    //println("puppa name"+ PuppaImpl())
    LarvaImpl(puppa.name +"new Puppa" ,
      puppa.boundingBox,
      puppa.direction,
      puppa.velocity+35,
      puppa.life +DEF_PUPPA_LIFE ,
      puppa.degradationEffect ,
      puppa.movementStrategy,
      puppa.lifeCycle,
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



