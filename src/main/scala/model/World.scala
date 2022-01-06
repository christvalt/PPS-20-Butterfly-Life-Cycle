package model

import controler.TimingOps.{Simulation, toStateTWorld}
import model.common.BoundingBox.{Rectangle, Triangle}
import model.common.Intersection.isCollidingWith
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlowerPlant, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl}
import model.common.Final.{BUTTERFLY_LIFE, BUTTERFLY_RADIUS, BUTTERFLY_VELOCITY, DEF_PREDATOR_PLANT_HEIGHT, DEF_PREDATOR_PLANT_WIDTH, ITERATIONS_PER_DAY, TEMPERATURE_AMPLITUDE, WORLD_HEIGHT, WORLD_WIDTH}
import model.common.{BoundingBox, Direction, Environment, MovingStrategies, Point2D}
import model.common.Point2D.{randomPosition, randomPositionForEgg}
import model.reaction.{DegenerationE, EatingEffect}
import utils.TrigonometricalOps.Sinusoidal.Curried.zeroPhasedZeroYTranslatedSinusoidal
import utils.TypeUtilities.SimulableEntity





/**
 *  Represent the state of the simuation and contains all  the creature durent the iteration
 *  params: temperature --the actual temperature of the system
 *  width: The width of the simulation
 *  height:   the height of the simulation
 *  cretature: the set of creature present inside the simulation
 *  currentIteration: the real time iteration
 *  TotalIteration: the total iteration before the end of simulation
 * */

case class  World(temperature:Int,
                  width :Int,
                  height :Int,
                  creature: Set[SimulableEntity],
                  currentIteration :Int,
                  totalIterations: Int)


/**
 * compagnon object of the World that contains all the different paraneter of world
 *
 * Represent the state of the simuation and contains all  the creature durent the iteration
 *  params: temperature --the actual temperature of the system
 *  cretature: the set of creature present inside the simulation
 *  currentIteration: the real time iteration
 *  TotalIteration: the total iteration before the end of simulation
 *
 * */

object  World{

  def apply(env:Environment):World={
    val iterationsPerDay =100


    val eggs: Set[SimulableEntity] = Iterator.tabulate(env.eggs)(i => EggsImpl(
      name = "eggs" + i,
      boundingBox = BoundingBox.Circle.apply(point = randomPositionForEgg(), radius = 7),
      Direction(0, 0),
      velocity = 3,
      life = 1200 ,
      degradationEffect = DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
      changeStage = DegenerationE.inc,
      lifeCycle = 0
    )).toSet

    val larva: Set[SimulableEntity] = Iterator.tabulate(env.larva)(i => LarvaImpl(
      name = "Larva" + i,
      boundingBox = BoundingBox.Circle.apply(point = randomPosition(), radius = 5),
      Direction(0, 20),
      velocity= 15,
      life=950,
      degradationEffect = DegenerationE.deacreaseLifeEffectForLarva ,
      movementStrategy = MovingStrategies.baseMovement,
      lifeCycle = 0,
      changeStage = DegenerationE.inc
    )).toSet


    val puppa: Set[SimulableEntity] = Iterator.tabulate(env.puppa)(i => PuppaImpl(
      name = "puppa" + i,
      boundingBox = BoundingBox.Circle.apply(point = randomPosition(), radius = 10),
      Direction(0, 20),
      velocity= 35,
      life=BUTTERFLY_LIFE ,
      degradationEffect=DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
      lifeCycle = 0,
      changeStage=DegenerationE.inc
    )).toSet

    val Adultbuttefly: Set[SimulableEntity] = Iterator.tabulate(env.buttefly)(i => ButterflyImpl(
      name = "AdultButtefly" + i,
      boundingBox = BoundingBox.Circle.apply(point = randomPosition(), radius = BUTTERFLY_RADIUS),
      Direction(0, 0),
      velocity= BUTTERFLY_VELOCITY,
      life=1200 ,
      degradationEffect = DegenerationE.deacreaseLifeEffect ,
      movementStrategy = MovingStrategies.baseMovement,
      lifeCycle = 0,
      changeStage = DegenerationE.inc
    )).toSet

    val predador : Set[SimulableEntity] =  Iterator.tabulate(env.predator)(i => PredatorImpl(
      name = "predator"+i,
      boundingBox = Rectangle.apply(point = randomPosition(), width = DEF_PREDATOR_PLANT_WIDTH, height = DEF_PREDATOR_PLANT_HEIGHT),
      collisionEffect =EatingEffect.collidedWithPredactor,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 5003,
      lifeCycle = 0,
      direction = Direction(20, 10),
      velocity = BUTTERFLY_VELOCITY,
      movementStrategy = MovingStrategies.baseMovement,
    )).toSet

    val nectarPlant: Set[SimulableEntity] = Iterator.tabulate(env.plant)(i => NectarPlant(
      name = "nectarPlant" +i,
      boundingBox = Triangle.apply(point =  randomPosition(), height = 10),
      collisionEffect =EatingEffect.iscollidedWithNectarPlant,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 5003,
      lifeCycle = 0

    )).toSet

    val simplePlan:Set[SimulableEntity] =  Iterator.tabulate(env.plant)(i => FlowerPlant(
      name = "flourPlant" + i ,
      boundingBox = Triangle.apply(point = randomPosition(),
        height = 10),
      collisionEffect =EatingEffect.collideWithSimplePlan,
      degradationEffect =DegenerationE.deacreaseLifeEffect,
      life = 5003,
      lifeCycle = 0

    )).toSet



  /**
   *   val leavesOfPlants: Set[SimulableEntity] = Iterator.tabulate(env.eggs)(i => LeavesOfPlants(name = "leaf " + i ,
      boundingBox = Rectangle.apply(point = randomPosition(), width = 150, height = 30),
      degradationEffect = DegenerationE.deacreaseLifeEffect,
      life = 10006,
      collisionEffect = EatingEffect.collidedWithPredactor,
      valor=eggs,
      lifeCycle = 0

    )).toSet*/


    val creature : Set [SimulableEntity]  = Adultbuttefly ++ larva ++ eggs ++puppa ++predador ++nectarPlant ++ simplePlan


    World(temperature = env.temperature ,
      width = WORLD_WIDTH,
      height = WORLD_HEIGHT,
      creature = creature,
      currentIteration = 0,
      totalIterations=env.days * iterationsPerDay)
  }



  /**Updating the world according to the temerature in the time of the day
   * */
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



  /**
   *  Upadates the state of  the world occording of the time of the day and the ininitial parameter
   *  params:world - the iinitila worls with the parameter to update
   *  Returns: the stare of world updated
   *
   * */
  def updateState(world: World):World= {

    val updateWorldParameters = updateStateOfWorldParameter(world)

    world.copy(
      temperature = updateWorldParameters.temperature,
      creature = world.creature.foldLeft(Set[SimulableEntity]())((updatedEntities, entity) => updatedEntities ++ entity.updateState(world)),
      currentIteration = world.currentIteration + 1,
    )

  }

  /** Check collisions between the different simmulable creature that are lives in the systems.
   * The detection between creature is noticed by the intersection of two figure that represent it
   *
   * params: the world that containt the creature
   * Returns: the  new state of world after observing the collision of different creature.
   *
   * */

  def checkCollision(world: World):World = {

    val  toTuple = Tuple2( world.creature,world.creature)
    val  collisionBoundsBox = for{
      i <- toTuple._1
      j <- toTuple._2

      if i!=j && isCollidingWith(i.boundingBox,j.boundingBox)
    } yield (i,j)

    def creatureCollided = collisionBoundsBox.map(_._1)
    def newCreatureEntitiesAfterCollision = collisionBoundsBox.foldLeft(world.creature -- creatureCollided)((entitiesAfterCollision, collision) => entitiesAfterCollision ++ collision._1.collision(collision._2))


    world.copy(
      creature=newCreatureEntitiesAfterCollision,
    )
  }

  /**
   * Returns the time of the day
   * params: iteration - iteratio number
   * Returns : time of the day
   *
   * */
  def timeOfTheDay(iteration: Int): Float =
    iteration % ITERATIONS_PER_DAY / ITERATIONS_PER_DAY.toFloat






  //type conversion
  /**provide a simple conversion from IO instance of updateState of the world to more general cats.date.StateT monad */
  def wordUpdateToState():Simulation[World] = toStateTWorld{
    updateState
  }
  /**provide a simple conversion from IO instance of checkCollision to more general cats.date.StteT monad */

  def checkCollisionToState():Simulation[World] = toStateTWorld{
    checkCollision
  }
}
