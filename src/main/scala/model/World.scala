package model

import cats.Traverse.ops.toAllTraverseOps
import model.BoundingBox.{Circle, Rectangle, Triangle}
import model.Intersection.isCollidingWith
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.common.{Environment, Point2D}
import model.common.Point2D.randomPosition
import model.creature.Behavior.SimulableEntity
import model.creature.CreatureObject.Creature
import model.creature.CreatureObject.Domain.Collision
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
  val DEF_BLOB_RADIUS = 5


  def apply(env:Environment):World={

    val buttefly: Set[ButterflyImpl] = Set(ButterflyImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val larva: Set[LarvaImpl] = Set(LarvaImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val eggs: Set[EggsImpl] = Set(EggsImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val puppa: Set[PuppaImpl] = Set(PuppaImpl(name = "blob" + nextValue(),
      boundingBox = BoundingBox.Circle(point = randomPosition(), radius = DEF_BLOB_RADIUS)))

    val predador : Set[PredatorImpl] = Set(PredatorImpl(name = "predator", boundingBox = Rectangle(point =randomPosition, width = DEF_PREDATOR_PLANT_WIDTH, height = DEF_PREDATOR_PLANT_HEIGHT),collisionEffect =EatingEffect.iscollidedWithPredactor,degradationEffect =DegenerationE.deacreaseLifeEffect,life = 22))

    val nectarPlant: Set[NectarPlant] =  Set(NectarPlant(name = "predator", boundingBox = Triangle(point = Point2D(100, 100), height = 10),collisionEffect =EatingEffect.iscollidedWithPredactor,degradationEffect =DegenerationE.deacreaseLifeEffect,life = 22))

    val simplePlan:Set[flourPlant] =  Set(flourPlant(name = "predator", boundingBox = Triangle(point = Point2D(100, 100), height = 10),collisionEffect =EatingEffect.iscollidedWithPredactor,degradationEffect =DegenerationE.deacreaseLifeEffect,life = 22))

    val creature : Set [SimulableEntity]  = buttefly ++ larva ++ eggs ++ puppa++ predador ++ nectarPlant++ simplePlan



    World(temperature = env.temperature , width = WORLD_WIDTH, height = WORLD_HEIGHT, creature = creature, currentIteration = 0,totalIterations=100)
  }

case class ParameterEnv(temperature: Int)

  def updateStateOfWorldParameter(world: World): ParameterEnv = {
    println("world")

    val temperatureUpdated: ((Int, Float)) => Int ={
      case (temperature, timeOfTheDay) =>
        temperature + zeroPhasedZeroYTranslatedSinusoidal(TEMPERATURE_AMPLITUDE)(timeOfTheDay)
    }

    val time = timeOfTheDay(world.currentIteration)
    ParameterEnv(
      temperatureUpdated(world.temperature, time))
  }

  def worldStteTotal(world: World): World= {

    updateState(world)
    checkCollision(world)
  }

  def updateState(world: World):World= {

    val updateWorldParameters = updateStateOfWorldParameter(world)

    world.copy(
      temperature = updateWorldParameters.temperature,
      creature = world.creature.foldLeft(Set[SimulableEntity]())((updatedEntities, entity) => updatedEntities ++ entity.updateState(world)),
      currentIteration = world.currentIteration + 1,
    )

    // call the update of thr collision afther the update of different entities in the system

    // checkCollision
  }


  def checkCollision(world: World):World = {

    val  toTuple = Tuple2( world.creature,world.creature)
    val  collisionBoundiBox = for{
      i <- toTuple._1
      j <- toTuple._2

      if i!=j && isCollidingWith(i.boundingBox,j.boundingBox)
    } yield (i,j)
    println("test"+collisionBoundiBox)

    def allcreatureCollided = collisionBoundiBox.map(_._1)
    def newCreatureEntitiesAfterCollision = collisionBoundiBox.foldLeft(world.creature -- allcreatureCollided)((entitiesAfterCollision, collision) => entitiesAfterCollision ++ collision._1.collision(collision._2))


    println("after" +newCreatureEntitiesAfterCollision)
    println("size" +newCreatureEntitiesAfterCollision.size)

    world.copy(
      creature=newCreatureEntitiesAfterCollision,
    )
  }


  def timeOfTheDay(iteration: Int): Float =
    iteration % ITERATIONS_PER_DAY / ITERATIONS_PER_DAY.toFloat


}






