package model.creature

import model.BoundingBox.Circle
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.{TemperatureEffect, World}
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}
import model.reaction.EatingEffect.MIN_BLOB_FOV_RADIUS

import scala.math.Ordered.orderingToOrdered

object Behavior {
  val DEF_BLOB_VELOCITY = 50
  val DEF_BLOB_SLOW_VELOCITY: Int = DEF_BLOB_VELOCITY / 2
  val MIN_BLOB_VELOCITY: Int = DEF_BLOB_SLOW_VELOCITY

  /// all the intity in the system

  trait Simulable extends Collidable with UpdatableEntity
  type SimulableEntity = Creature with Simulable

  object Simulable {

    self:Creature =>

  }




  trait EggsBehavior extends Simulable {
    self: EggsImpl =>
  //  val size :Int

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)

      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        /*movementDirection = movement.angle,
        stepToNextDirection = movement.stepToNextDirection,*/
        life = self.degradationEffect(self),
        fieldOfViewRadius = self.fieldOfViewRadius + world.temperature
      ))


    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }


  trait EggsBehavior2LarvaBehavior extends Simulable {
    self: LarvaImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
       // case n if n > 0 => Set(LarvaImpl(self.name,self.boundingBox,self.direction,self.fieldOfViewRadius))
        case _ =>Set()
      }
    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }


//      case base: BaseBlob => if (self.boundingBox.radius >= base.boundingBox.radius) Set(self.copy(life = self.life + base.life)) else Set(self.copy())

  trait LarvaBehavior extends Simulable {
    self: LarvaImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)

      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        /*movementDirection = movement.angle,
        stepToNextDirection = movement.stepToNextDirection,*/
        life = self.degradationEffect(self),
        fieldOfViewRadius = self.fieldOfViewRadius + world.temperature
      ))


    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }

  }


  trait PuppaImplBehavior extends  Simulable  {
    self: PuppaImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)

      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        /*movementDirection = movement.angle,
        stepToNextDirection = movement.stepToNextDirection,*/
        life = self.degradationEffect(self),
        fieldOfViewRadius = self.fieldOfViewRadius + world.temperature
      ))


    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }


  trait butterflyBehavior extends Simulable {
    self: ButterflyImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)

      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        /*movementDirection = movement.angle,
        stepToNextDirection = movement.stepToNextDirection,*/
        life = self.degradationEffect(self),
        fieldOfViewRadius = self.fieldOfViewRadius + world.temperature
      ))


    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }



  trait PlantBehavior extends Simulable {
    self: flourPlant =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(flourPlant(self.name, self.boundingBox,self.degradationEffect,newState,self.collisionEffect))
        case _ =>Set()
      }
    }
    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait NectarPlantBehavior extends  Simulable {
    self: NectarPlant =>
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(NectarPlant(self.name, self.boundingBox,self.degradationEffect,newState,self.collisionEffect))
        case _ =>Set()
      }
    }
    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait PredatorBehavior extends Simulable  {
    self: PredatorImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(PredatorImpl(self.name, self.boundingBox,self.degradationEffect,newState,self.collisionEffect))
        case _ =>Set()
      }
    }
    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }

   /*
   *  def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
     // case  plant: Plant => plant.collisionEffect(self)
      case  buttefly:ButterflyImpl => Set(self.copy(life = self.life + buttefly.life))
      case  egg:EggsImpl => Set(self.copy(life = self.life + egg.life))
      case  larva:LarvaImpl => Set(self.copy(life = self.life + larva.life))
      case _ => Set(self)
    }*/
  }


  protected[model] def updateBlob[A <: Butterfly](self: A, movement: Movement, world: World): SimulableEntity = self match {
    case base: EggsImpl => base.copy(
      boundingBox = base.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = incrementedValue(base.velocity, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_VELOCITY),
      life = base.degradationEffect(base),
      fieldOfViewRadius = incrementedValue(base.fieldOfViewRadius,TemperatureEffect.standardTemperatureEffect,world, MIN_BLOB_FOV_RADIUS)
    )
    case cannibal: LarvaImpl => cannibal.copy(
      boundingBox = cannibal.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = incrementedValue(cannibal.velocity, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_VELOCITY),
      life = cannibal.degradationEffect(cannibal),
      fieldOfViewRadius = incrementedValue(cannibal.fieldOfViewRadius, TemperatureEffect.standardTemperatureEffect, world,MIN_BLOB_FOV_RADIUS)
    )
    case slow: LarvaImpl => slow.copy(
      boundingBox = slow.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = DEF_BLOB_SLOW_VELOCITY,
      life = slow.degradationEffect(slow),
      fieldOfViewRadius = incrementedValue(slow.fieldOfViewRadius, TemperatureEffect.standardTemperatureEffect,  world, MIN_BLOB_FOV_RADIUS),
    )
    case poison: ButterflyImpl => poison.copy(
      boundingBox = poison.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = incrementedValue(poison.velocity, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_VELOCITY),
      life = poison.degradationEffect(poison),
      fieldOfViewRadius = incrementedValue(poison.fieldOfViewRadius, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_FOV_RADIUS),
    )
    case _ => throw new Exception("Sub type not supported.")
  }


  private def incrementedValue(value: Int, effect: ((Int, Int)) => Int, world: World, min: Int): Int = {
    (value + effect(world.temperature, world.currentIteration)).max(min)
  }
}



