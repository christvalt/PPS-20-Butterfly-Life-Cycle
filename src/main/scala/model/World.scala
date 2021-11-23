package model

import model.BoundingBox.{Circle, Rectangle}
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.Point2D.randomPosition
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.Creature
import model.creature.CreatureObject.Domain.Collision
import model.reaction.EatingEffect.Counter.nextValue
import utils.TrigonometricalOps.Sinusoidal.Curried.zeroPhasedZeroYTranslatedSinusoidal

case class  World(temperature:Int, width :Int, height :Int, creature: Set[SimulableEntity], currentIteration :Int, totalIterations: Int)


object  World{
  val WORLD_WIDTH = 1280
  val WORLD_HEIGHT = 720
  val TEMPERATURE_AMPLITUDE = 1.0125f
  val DEF_PREDATOR_PLANT_WIDTH = 8
  val DEF_PREDATOR_PLANT_HEIGHT = 8
  val ITERATIONS_PER_DAY = 100
  val DEF_BLOB_RADIUS = 5


  case class EnvironmentParameters(temperature: Int)

  case class Environment(temperature: Int,
                         buttefly: Int,
                         plant: Int,
                         predator: Int,
                         days: Int)

  def apply(env:Environment):World={

    val buttefly: Set[ButterflyImpl] = Set(ButterflyImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val larva: Set[LarvaImpl] = Set(LarvaImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val eggs: Set[EggsImpl] = Set(EggsImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val catepilar: Set[PuppaImpl] = Set(PuppaImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val predador : Set[PredatorImpl] = Set(PredatorImpl(name = "predator", boundingBox = Rectangle(point =randomPosition, width = DEF_PREDATOR_PLANT_WIDTH, height = DEF_PREDATOR_PLANT_HEIGHT), life = ???, velocity = ???, collisionEffect = ???))

    val nectarPlant: Set[NectarPlant] = Set(NectarPlant(boundingBox = ???, degradationEffect = ???, life = ???, name = ???, collisionEffect = ???))

    val simplePlan:Set[flourPlant] = Set(flourPlant(boundingBox = ???, degradationEffect = ???, life = ???, name = ???, collisionEffect = ???))

    val creature : Set [SimulableEntity]  = buttefly ++ larva ++ eggs ++ catepilar++ predador ++ nectarPlant++ simplePlan



    World(temperature = env.temperature , width = WORLD_WIDTH, height = WORLD_HEIGHT, creature = creature, currentIteration = 0,totalIterations=100)
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

  def updateState(world: World):World= {

    val updateWorldParameters = updateStateOfWorldParameter(world)

    world.copy(
      temperature = updateWorldParameters.temperature,
      creature = world.creature.foldLeft(Set[SimulableEntity]())((updatedEntities, entity) => updatedEntities ++ entity.updateState(world)),
      currentIteration = world.currentIteration + 1,
    )

  }


  def timeOfTheDay(iteration: Int): Float =
    iteration % ITERATIONS_PER_DAY / ITERATIONS_PER_DAY.toFloat


}






