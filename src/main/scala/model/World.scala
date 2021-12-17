package model

import controler.TimingOps.{Simulation, toStateTWorld}
import model.common.BoundingBox.{Rectangle, Triangle}
import model.common.Intersection.isCollidingWith
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlourPlant, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl}
import model.common.{BoundingBox, Direction, Environment, MovingStrategies, Point2D}
import model.common.Point2D.randomPosition
import model.creature.Behavior
import model.creature.Behavior.SimulableEntity
import model.reaction.DegenerationE
import model.reaction.{DegenerationE, EatingEffect}
import utils.TrigonometricalOps.Sinusoidal.Curried.zeroPhasedZeroYTranslatedSinusoidal



case class  World(temperature:Int,
                  width :Int,
                  height :Int,
                  creature: Set[SimulableEntity],
                  currentIteration :Int,
                  totalIterations: Int)


object  World{

  val WORLD_WIDTH = 1280
  val WORLD_HEIGHT = 720
  val TEMPERATURE_AMPLITUDE = 1.0125f
  val DEF_PREDATOR_PLANT_WIDTH = 8
  val DEF_PREDATOR_PLANT_HEIGHT = 12
  val ITERATIONS_PER_DAY = 100
  val BUTTERFLY_RADIUS = 30
  val DEF_BLOB_FOW_RADIUS= 10
  val BUTTERFLY_VELOCITY = 70
  val BUTTERFLY_LIFE = 300


  def apply(env:Environment):World={
    val iterationsPerDay =100


    val eggs: Set[SimulableEntity] = Iterator.tabulate(env.eggs)(i => EggsImpl(
      name = "eggs" + i,
      boundingBox = BoundingBox.Circle.apply(point = randomPosition(),radius = 5),
      Direction(0, 0),
      velocity= 3,
      life=BUTTERFLY_LIFE ,
      degradationEffect = DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
      //lifeCycle = updateLifeCyclecreatureOb
    )).toSet

    val larva: Set[SimulableEntity] = Iterator.tabulate(env.larva)(i => LarvaImpl(
      name = "Larva" + i,
      boundingBox = BoundingBox.Circle.apply(point =  Point2D(150, 130),radius = 15),
      Direction(0, 20),
      velocity= 15,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
     // lifeCycle = updateLifeCyclecreatureOb
    )).toSet


    val puppa: Set[SimulableEntity] = Iterator.tabulate(env.puppa)(i => PuppaImpl(
      name = "puppa" + i,
      boundingBox = BoundingBox.Circle.apply(point =  Point2D(444, 355),radius = 20),
      Direction(0, 20),
      velocity= 35,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
     // lifeCycle = updateLifeCyclecreatureOb
    )).toSet

    val Adultbuttefly: Set[SimulableEntity] = Iterator.tabulate(env.buttefly)(i => ButterflyImpl(
      name = "AdultButtefly" + i,
      boundingBox = BoundingBox.Circle.apply(point =  Point2D(5, 5),radius = BUTTERFLY_RADIUS),
      Direction(0, 0),
      velocity= BUTTERFLY_VELOCITY,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
      //lifeCycle = updateLifeCyclecreatureOb
    )).toSet

    val predador : Set[SimulableEntity] =  Iterator.tabulate(env.predator)(i => PredatorImpl(
      name = "predator"+i,
      boundingBox = Rectangle.apply(point = randomPosition(), width = DEF_PREDATOR_PLANT_WIDTH, height = DEF_PREDATOR_PLANT_HEIGHT),
      collisionEffect =EatingEffect.iscollidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 5003
    )).toSet

    val nectarPlant: Set[SimulableEntity] = Iterator.tabulate(env.plant)(i => NectarPlant(
      name = "nectarPlant" +i,
      boundingBox = Triangle.apply(point =  randomPosition(), height = 10),
      collisionEffect =EatingEffect.iscollidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 5003
    )).toSet

    val simplePlan:Set[SimulableEntity] =  Iterator.tabulate(env.plant)(i => FlourPlant(
      name = "flourPlant" + i ,
      boundingBox = Triangle.apply(point = randomPosition(),
        height = 10),
      collisionEffect =EatingEffect.iscollidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 5003
    )).toSet


    val creature : Set [SimulableEntity]  = Adultbuttefly ++ larva ++ eggs ++puppa ++predador ++nectarPlant ++ simplePlan

    println("testingìììììììììììììììììììììììììììì"+List(creature))
    println("--------------------"+creature.size)
    println("+++++++++++++++++++"+Adultbuttefly.size)
    println("********************"+larva.size)
    println("°°°°°°°°°°°°°°°°°"+eggs.size)
    println("°°°°°°°°°°°°°°°°°"+puppa.size)
    println("°°°°°°°°°°°°°°°°°"+predador.size)
    println("°°°°°°°°°°°°°°°°°"+nectarPlant.size)
    println("°°°°°°°°°°°°°°°°°"+simplePlan.size)


    World(temperature = env.temperature ,
      width = WORLD_WIDTH,
      height = WORLD_HEIGHT,
      creature = creature,
      currentIteration = 0,
      totalIterations=env.days * iterationsPerDay)
  }


case class ParameterEnv(temperature: Int)

  def updateStateOfWorldParameter(world: World): ParameterEnv = {

    val temperatureUpdated: ((Int, Float)) => Int ={
      case (temperature, timeOfTheDay) =>
        temperature + zeroPhasedZeroYTranslatedSinusoidal(TEMPERATURE_AMPLITUDE)(timeOfTheDay)
    }

    val time = timeOfTheDay(world.currentIteration)
    ParameterEnv(
      temperatureUpdated(world.temperature, time))
  }


  def wordUpdateToState():Simulation[World] = toStateTWorld{
    updateState
  }

  def checkCollisionToState():Simulation[World] = toStateTWorld{
    checkCollision
  }


  def updateState(world: World):World= {

    val updateWorldParameters = updateStateOfWorldParameter(world)

    world.copy(
      temperature = updateWorldParameters.temperature,
      creature = world.creature.foldLeft(Set[SimulableEntity]())((updatedEntities, entity) => updatedEntities ++ entity.updateState(world)),
      currentIteration = world.currentIteration + 1,
    )

  }


  def checkCollision(world: World):World = {

    val  toTuple = Tuple2( world.creature,world.creature)
    val  collisionBoundiBox = for{
      i <- toTuple._1
      j <- toTuple._2

      if i!=j && isCollidingWith(i.boundingBox,j.boundingBox)
    } yield (i,j)

    def allcreatureCollided = collisionBoundiBox.map(_._1)
    def newCreatureEntitiesAfterCollision = collisionBoundiBox.foldLeft(world.creature -- allcreatureCollided)((entitiesAfterCollision, collision) => entitiesAfterCollision ++ collision._1.collision(collision._2))


    world.copy(
      creature=newCreatureEntitiesAfterCollision,
    )
  }


  def timeOfTheDay(iteration: Int): Float =
    iteration % ITERATIONS_PER_DAY / ITERATIONS_PER_DAY.toFloat
}
