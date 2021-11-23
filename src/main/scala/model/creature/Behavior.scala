package model.creature

import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.World
import model.creature.Behavior.SimulableEntity
import model.creature.Collidable.NeutralCollidable
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}

object Behavior {

  /// all the intity in the system
  trait Simulable extends Collidable with UpdatableEntity
  type SimulableEntity = Creature with Simulable

  object Simulable {

    self:Creature =>

  }

  trait EggsBehavior extends Simulable {
    self: EggsImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n < 0 => Set(PuppaImpl(self.name,self.boundingBox,self.direction,self.fieldOfViewRadius))
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

  trait PuppaImplBehavior extends  Simulable  {
    self: PuppaImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      val newState = self.degradationEffect(self)
      newState match {
        case n if n < 0 => Set(PuppaImpl(self.name,self.boundingBox,self.direction,self.fieldOfViewRadius))
        case _ =>Set()
      }
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
     ???
    }
     def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }

  trait LarvaBehavior extends Simulable {
    self: LarvaImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
     ???
    }
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  creature: Predator => creature.collisionEffect(self)
      case _ => Set(self)
    }

  }

  trait PlantBehavior extends Simulable {
    self: flourPlant =>

    override def updateState(world:World): Set[SimulableEntity]={
     ???
    }
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait NectarPlantBehavior extends  Simulable {
    self: NectarPlant =>
    override def updateState(world:World): Set[SimulableEntity]={
     ???
    }
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait PredatorBehavior extends Simulable  {
    self: PredatorImpl =>

    override def updateState(world:World): Set[SimulableEntity]={
      ???
    }
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
     // case  plant: Plant => plant.collisionEffect(self)
      case  buttefly:ButterflyImpl => Set(self.copy(life = self.life + buttefly.life))
      case  egg:EggsImpl => Set(self.copy(life = self.life + egg.life))
      case  larva:LarvaImpl => Set(self.copy(life = self.life + larva.life))
      case _ => Set(self)
    }
  }
}



