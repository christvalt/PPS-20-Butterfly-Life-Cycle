package model.creature

import model.common.BoundingBox.{Circle, Rectangle}
import model.SimulationObjectImpl.{ButterflyImpl, EggsImpl, FlowerPlant, LarvaImpl, LeavesOfPlants, NectarPlant, PredatorImpl, PuppaImpl}
import model. World
import model.creature.CreatureObject.{Butterfly, Creature, Plant, Predator}
import model.reaction.DegenerationE
import utils.TypeUtilities.Life

/**
 * Specific entities of butterfly life cycle behaviors implementation
 */

object Behavior {


  trait Simulable extends Collidable with UpdatableEntity

  type SimulableEntity = Creature with Simulable

  object Simulable {
    self: Creature =>
  }


  trait EggsBehavior extends Simulable {
    self: EggsImpl =>
    // def lifeCycle = self.life-1
    override def updateState(world: World): Set[SimulableEntity] = {
      val newPosition = self.movementStrategy(self, world)
      self.life match {
        case n if n > 0 => self.lifeCycle match {
          case m if m == 500 => Set(DegenerationE.helperEggToLarva(self))
          case _ => Set(self.copy(
            boundingBox = Circle(newPosition.point, self.boundingBox.radius),
            direction = newPosition.direction,
            life = self.degradationEffect(self),
            lifeCycle = self.changeStage(self)))
        }
        case _ => Set()
      }
    }

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case plant: Plant => plant.collisionEffect(self)
      case predator: Predator => predator.collisionEffect(self)
      case _ => Set(self)
    }

    def incrementLifeCy: () => Life = () => self.lifeCycle + 1 //self.changeStage(self)
  }

  //comment of change
  trait LarvaBehavior extends Simulable {
    self: LarvaImpl =>
    override def updateState(world: World): Set[SimulableEntity] = {
      val newState = self.movementStrategy(self, world)
      self.life match {
        case n if n > 0 => world.currentIteration match {
          case m if m == 700 => Set(DegenerationE.helperLarvaToAdult(self))
          case _ => Set(self.copy(
            boundingBox = Circle(newState.point, 5), //Circle(Position=Point(2,4) , radius=5)
            direction = newState.direction,
            life = self.degradationEffect(self)))
        }
        case _ => Set()
      }
    }

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case plant: Plant => plant.collisionEffect(self)
      case predator: Predator => predator.collisionEffect(self)
      case _ => Set(self)
    }
  }


  trait PuppaBehavior extends Simulable {
    self: PuppaImpl =>
    //  println("puppa entities*****++***++*++*++*******" +self )
    override def updateState(world: World): Set[SimulableEntity] = {
      val newState = self.movementStrategy(self, world)
      self.life match {
        case n if n > 0 => world.currentIteration match {
          case m if m == 1000 => Set(DegenerationE.helperLarvaToPuppa(self))
          case _ => Set(self.copy(
            boundingBox = Circle(newState.point, self.boundingBox.radius), //Circle(Position=Point(2,4) , radius=5)
            direction = newState.direction,
            life = self.degradationEffect(self)))
        }
        case _ => Set()
      }
    }

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case plant: Plant => plant.collisionEffect(self)
      case predator: Predator => predator.collisionEffect(self)
      case _ => Set(self)
    }
  }


  trait butterflyBehavior extends Simulable {
    self: ButterflyImpl =>
    override def updateState(world: World): Set[SimulableEntity] = {
      val newState = self.movementStrategy(self, world)
      Set(self.copy(
        boundingBox = Circle(newState.point, self.boundingBox.radius),
        direction = newState.direction,
        life = self.degradationEffect(self)
      ))
    }

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case plant: Plant => plant.collisionEffect(self)
      case predator: Predator => Set(self.copy(life = self.life - predator.life))
      case _ => Set(self)
    }
  }

  trait PlantBehavior extends Simulable {
    self: FlowerPlant =>
    override def updateState(world: World): Set[SimulableEntity] = {
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(FlowerPlant(self.name, self.boundingBox, self.degradationEffect, newState, self.collisionEffect, self.lifeCycle))
        case _ => Set()
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
    override def updateState(world: World): Set[SimulableEntity] = {
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(LeavesOfPlants(self.name,
          self.boundingBox,
          self.degradationEffect,
          newState,
          self.collisionEffect,
          self.lifeCycle))
        case _ => Set()
      }
    }

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }

  }


  trait NectarPlantBehavior extends Simulable {
    self: NectarPlant =>
    override def updateState(world: World): Set[SimulableEntity] = {
      val newState = self.degradationEffect(self)
      newState match {
        case n if n > 0 => Set(NectarPlant(self.name, self.boundingBox, self.degradationEffect, newState, self.collisionEffect, self.lifeCycle))
        case _ => Set()
      }
    }

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }

  trait PredatorBehavior extends Simulable {
    self: PredatorImpl =>

    override def updateState(world: World): Set[SimulableEntity] = {
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

    override def collision(other: SimulableEntity): Set[SimulableEntity] = other match {
      case _: Butterfly => Set()
      case _ => Set(self)
    }
  }
}



