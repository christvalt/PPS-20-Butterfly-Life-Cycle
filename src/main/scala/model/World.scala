package model

import controler.TimingOps.{Simulation, toStateTWorld}
import model.BoundingBox.{Rectangle, Triangle}
import model.Intersection.isCollidingWith
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.common.{Environment, Point2D}
import model.common.Point2D.randomPosition
import model.creature.Behavior.SimulableEntity
import model.creature.{Direction, MovingStrategies}
import model.reaction.{DegenerationE, EatingEffect}
import model.reaction.EatingEffect.Counter.nextValue
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
  val DEF_PREDATOR_PLANT_HEIGHT = 8
  val ITERATIONS_PER_DAY = 100
  val BUTTERFLY_RADIUS = 5
  val DEF_BLOB_FOW_RADIUS= 10
  val BUTTERFLY_VELOCITY = 50
  val BUTTERFLY_LIFE = 300


  def apply(env:Environment):World={
    val iterationsPerDay =100

    val buttefly: Set[SimulableEntity] = Iterator.tabulate(env.buttefly)(i => ButterflyImpl(
      name = "blob" + i,
      boundingBox = BoundingBox.Circle.apply(point =  Point2D(5, 5),radius = BUTTERFLY_RADIUS),
      Direction(0, 0),
      fieldOfViewRadius=5,
      velocity= BUTTERFLY_VELOCITY,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement
    )).toSet

    val larva: Set[LarvaImpl] = Set(LarvaImpl(
      name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition() ,radius = BUTTERFLY_RADIUS),
      Direction(0, 20),
      fieldOfViewRadius=12,
      velocity= BUTTERFLY_VELOCITY ,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement)
    )

    val eggs: Set[EggsImpl] = Set(EggsImpl(
      name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition() , radius = BUTTERFLY_RADIUS),
      Direction(0, 20),
      fieldOfViewRadius=12,
      velocity= BUTTERFLY_VELOCITY ,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement)
    )

    val puppa: Set[PuppaImpl] = Set(PuppaImpl(
      name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition() ,
        radius = BUTTERFLY_RADIUS),
      Direction(0, 20),
      fieldOfViewRadius=12,
      velocity= BUTTERFLY_VELOCITY ,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement)
    )

    val predador : Set[PredatorImpl] = Set(PredatorImpl(name = "predator",
      boundingBox = Rectangle(point =randomPosition, width = DEF_PREDATOR_PLANT_WIDTH, height = DEF_PREDATOR_PLANT_HEIGHT),
      collisionEffect =EatingEffect.iscollidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 22)
    )

    val nectarPlant: Set[NectarPlant] =  Set(NectarPlant(name = "predator",
      boundingBox = Triangle(point = Point2D(100, 100), height = 10),
      collisionEffect =EatingEffect.iscollidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,life = 22)
    )

    val simplePlan:Set[flourPlant] =  Set(flourPlant(name = "predator",
      boundingBox = Triangle(point = Point2D(100, 100),
        height = 10),collisionEffect =EatingEffect.iscollidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,life = 22)
    )
    val creature : Set [SimulableEntity]  = buttefly

    println("testingìììììììììììììììììììììììììììì"+List(creature))

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






