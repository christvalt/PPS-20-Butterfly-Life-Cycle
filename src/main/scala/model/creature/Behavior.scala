package model.creature

import model.CreatureImpl.{ButterflyImpl, EggsImpl, LarvaImpl, NectarPlant, PredatorImpl, PuppaImpl, flourPlant}
import model.creature.Behavior.SimulableEntity
import model.creature.Collidable.NeutralCollidable
import model.creature.creatureStructure.{Butterfly, Creature, Plant, Predator}

object Behavior {

  /// all the intity in the system
  trait Simulable extends Collidable
  type SimulableEntity = Creature //with Simulable

  object Simulable {

    trait NeutralBehaviour extends NeutralCollidable {
      self:Creature =>
    }
  }

  trait EggsBehavior {
    self: EggsImpl =>
    def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }
//      case base: BaseBlob => if (self.boundingBox.radius >= base.boundingBox.radius) Set(self.copy(life = self.life + base.life)) else Set(self.copy())

  trait PuppaImplBehavior {
    self: PuppaImpl =>
    def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }


  trait butterflyBehavior {
    self: ButterflyImpl =>
     def collision(other: SimulableEntity):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  predator:Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }

  trait LarvaBehavior{
    self: LarvaImpl =>
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
      case  plant: Plant => plant.collisionEffect(self)
      case  creature: Predator => creature.collisionEffect(self)
      case _ => Set(self)
    }

  }

  trait PlantBehavior{
    self: flourPlant =>
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait NectarPlantBehavior{
    self: NectarPlant =>
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait PredatorBehavior{
    self: PredatorImpl =>
    def collision(other: Set[SimulableEntity]):Set[SimulableEntity] = other match{
     // case  plant: Plant => plant.collisionEffect(self)
      case  buttefly:ButterflyImpl => Set(self.copy(life = self.life + buttefly.life))
      case  egg:EggsImpl => Set(self.copy(life = self.life + egg.life))
      case  larva:LarvaImpl => Set(self.copy(life = self.life + larva.life))
      case _ => Set(self)
    }
  }
}



