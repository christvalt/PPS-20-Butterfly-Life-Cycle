package model.creature

import model.common.BoundingBox.{Circle, Rectangle}
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlourPlant, LarvaImpl, LeavesOfPlants, NectarPlant, PredatorImpl, PuppaImpl}
import model.World
import model.common.Final.EGG_RADIUS_ADD
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}
import model.reaction.DegenerationE
import utils.TypeUtilities.Life

object Behavior {



  trait Simulable extends Collidable with UpdatableEntity
  type SimulableEntity = Creature with Simulable

  object Simulable {
    self:Creature =>
  }


  trait EggsBehavior extends Simulable {
    self: EggsImpl =>
    override def updateState(world:World): Set[SimulableEntity] = {
      val newPosition = self.movementStrategy(self, world)
      self.life match {
        case n if n > 0 => self.lifeCycle match {
          case  m if m == 500 => Set(DegenerationE.helperEggToLarva(self))
          case _ => Set(self.copy(
            boundingBox = Circle(newPosition.point, self.boundingBox.radius+EGG_RADIUS_ADD),
            direction = newPosition.direction,
            life = self.degradationEffect(self),
            lifeCycle = self.changeStage(self)))
        }
        case  _ =>  Set()
      }
    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator: Predator => predator.collisionEffect(self)
      case _ => Set(self)
    }
  }


  trait LarvaBehavior extends Simulable {
    self: LarvaImpl =>
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)
      self.life match {
        case n if n > 0 => self.lifeCycle  match {
          case  m if m==900 => Set(DegenerationE.helperLarvaToPuppa(self))
          case _ => Set(self.copy(
            boundingBox = Circle(newState.point, self.boundingBox.radius),
            direction = newState.direction,
            life = self.degradationEffect(self),
            lifeCycle = self.changeStage(self)))
        }
        case  _ =>  Set()
      }
    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => predator.collisionEffect(self)
      case _ => Set(self)
    }
  }


  trait PuppaImplBehavior extends  Simulable  {
    self: PuppaImpl =>
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)
      println("puppa"+ life)

      self.life match {
        case n if n > 0 => self.lifeCycle   match {
          case  m if m==1150 => Set(DegenerationE.helperPuppaToAdult(self))
          case _ => Set(self.copy(
            boundingBox = Circle(newState.point, self.boundingBox.radius),//Circle(Position=Point(2,4) , radius=5)
            direction = newState.direction,
            life = self.degradationEffect(self),
            lifeCycle = self.changeStage(self)))
        }
        case  _ =>  Set()
      }
    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => predator.collisionEffect(self)
      case _ => Set(self)
    }
  }


  trait butterflyBehavior extends Simulable {
    self: ButterflyImpl =>
    override def updateState(world:World): Set[SimulableEntity] = {
      val newState = self.movementStrategy(self, world)
      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        life = self.degradationEffect(self)
      ))
    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }

  trait PlantBehavior extends Simulable {
    self: FlourPlant =>
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(FlourPlant(self.name, self.boundingBox,self.degradationEffect,newState,self.collisionEffect,self.lifeCycle))
        case _ =>Set()
      }
    }
    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }




  trait LeavesBehavior extends Simulable {
    self: LeavesOfPlants =>
   // println("leaving leaf" + self )
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(LeavesOfPlants(self.name,
          self.boundingBox,
          self.degradationEffect,
          newState,
          self.collisionEffect,
          self.valor,
          self.lifeCycle))
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
        case n if n > 0 => Set(NectarPlant(self.name, self.boundingBox,self.degradationEffect,newState,self.collisionEffect,self.lifeCycle))
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
     // val newState = self.degradationEffect(self)
      val newPosition = self.movementStrategy(self, world)
    //  println("jai"+self.life)
      self.life match {
        case n if n > 0 => Set(self.copy(
          boundingBox = Rectangle.apply(newPosition.point, self.boundingBox.width, self.boundingBox.height),
          direction = newPosition.direction,
          life = self.degradationEffect(self)
        ))
        case _ => Set()
      }

    }
    override def collision(other: SimulableEntity): Set[SimulableEntity] =  other match {
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


  /*
  *
  *
  * protected[model] def updateBlob[A <: Butterfly](self: A, movement: Movement, world: World): SimulableEntity = self match {
    case base: EggsImpl => base.copy(
      boundingBox = base.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = incrementedValue(base.velocity, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_VELOCITY),
      life = base.degradationEffect(base),
      //fieldOfViewRadius = incrementedValue(base.fieldOfViewRadius,TemperatureEffect.standardTemperatureEffect,world, MIN_BLOB_FOV_RADIUS)
    )
    case cannibal: LarvaImpl => cannibal.copy(
      boundingBox = cannibal.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = incrementedValue(cannibal.velocity, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_VELOCITY),
      life = cannibal.degradationEffect(cannibal),
      //fieldOfViewRadius = incrementedValue(cannibal.fieldOfViewRadius, TemperatureEffect.standardTemperatureEffect, world,MIN_BLOB_FOV_RADIUS)
    )
    case slow: LarvaImpl => slow.copy(
      boundingBox = slow.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = DEF_BLOB_SLOW_VELOCITY,
      life = slow.degradationEffect(slow),
      //fieldOfViewRadius = incrementedValue(slow.fieldOfViewRadius, TemperatureEffect.standardTemperatureEffect,  world, MIN_BLOB_FOV_RADIUS),
    )
    case poison: ButterflyImpl => poison.copy(
      boundingBox = poison.boundingBox.copy(point = movement.point),
      direction = movement.direction,
      velocity = incrementedValue(poison.velocity, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_VELOCITY),
      life = poison.degradationEffect(poison),
      //fieldOfViewRadius = incrementedValue(poison.fieldOfViewRadius, TemperatureEffect.standardTemperatureEffect, world, MIN_BLOB_FOV_RADIUS),
    )
    case _ => throw new Exception("Sub type not supported.")
  }


  private def incrementedValue(value: Int, effect: ((Int, Int)) => Int, world: World, min: Int): Int = {
    (value + effect(world.temperature, world.currentIteration)).max(min)
  }*/
}



