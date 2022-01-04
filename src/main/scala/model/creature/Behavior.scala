package model.creature

import model.common.BoundingBox.{Circle, Rectangle}
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlowerPlant, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl}
import model.World
import model.common.Final.EGG_RADIUS_ADD
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}
import model.reaction.DegenerationE


/**
 * Specific behaviors implementation of different creature
 */

object Behavior {

  trait Simulable extends Collidable with UpdatableEntity


  /**Simple type to define the creature contained in the world*/
  type SimulableEntity = Creature with Simulable

  /**This represent the compagnon object of simulable
   *
   * */
  object Simulable {
    self:Creature =>
  }

  /**
   * This Behaviour represent the base behaviour mainly for a Base Egg It contains two methods:
   * updateState: check either the Egg is dead or not. It returns a set containing the new Egg with the updated values or an empty set if the Egg is dead.
   * collision: when the Egg collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */
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

  /**
   * This Behaviour represent the base behaviour mainly for a Base LarvaImpl It contains two methods:
   * updateState: check either the LarvaImpl is dead or not. It returns a set containing the new LarvaImpl with the updated values or an empty set if the Egg is dead.
   * collision: when the LarvaImpl collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */
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

  /**
   * This Behaviour represent the base behaviour mainly for a Base PuppaImpl It contains two methods:
   * updateState: check either the PuppaImpl is dead or not. It returns a set containing the new PuppaImpl with the updated values or an empty set if the Egg is dead.
   * collision: when the PuppaImpl collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */
  trait PuppaImplBehavior extends  Simulable  {
    self: PuppaImpl =>
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.movementStrategy(self, world)
      println("puppa"+ life)

      self.life match {
        case n if n > 0 => self.lifeCycle   match {
          case  m if m==1150 => Set(DegenerationE.helperPuppaToAdult(self))
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


  /**
   * This Behaviour represent the base behaviour mainly for a Base ButterflyImpl It contains two methods:
   * updateState: check either the ButterflyImpl is dead or not. It returns a set containing the new ButterflyImpl with the updated values or an empty set if the ButterflyImpl is dead.
   * collision: when the ButterflyImpl collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */

  trait butterflyBehavior extends Simulable {
    self: ButterflyImpl =>
    override def updateState(world:World): Set[SimulableEntity] = {
      val newState = self.movementStrategy(self, world)
      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        life = self.degradationEffect(self),
        lifeCycle = self.changeStage(self)
      ))
    }
    override def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }


  /**
   * This Behaviour represent the base behaviour mainly for a Base FlowerPlant It contains two methods:
   * updateState: check either the FlowerPlant is dead or not. It returns a set containing the new FlowerPlant with the updated values or an empty set if the Egg is dead.
   * collision: when the FlowerPlant collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */
  trait PlantBehavior extends Simulable {
    self: FlowerPlant =>
    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(FlowerPlant(self.name, self.boundingBox,self.degradationEffect,newState,self.collisionEffect,self.lifeCycle))
        case _ =>Set()
      }
    }
    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }


  /**
   * This Behaviour represent the base behaviour mainly for a Base Egg It contains two methods:
   * updateState: check either the Egg is dead or not. It returns a set containing the new Egg with the updated values or an empty set if the Egg is dead.
   * collision: when the Egg collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */

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

  /**
   * This Behaviour represent the base behaviour mainly for a Base Egg It contains two methods:
   * updateState: check either the Egg is dead or not. It returns a set containing the new Egg with the updated values or an empty set if the Egg is dead.
   * collision: when the Egg collide with an creature this method is called. It can collide with different creature of simulation :
   *
   */
  trait PredatorBehavior extends Simulable  {
    self: PredatorImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newPosition = self.movementStrategy(self, world)

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

  }

}



