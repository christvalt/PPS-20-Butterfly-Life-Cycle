import org.scalatest._
import funspec._
import model.World
import model.common.Environment
import model.reaction.EatingEffect.collisionREceptivePLan

class WordTest extends AnyFunSpec {


  val initialWorld : Environment = {
    Environment(
      temperature=2,
      buttefly=2,
      plant =2 ,
      predator=3,
      days=2
    )
  }
  val  initialWorld2: World = World(initialWorld)

  describe("A  World") {
    describe("when initialized ") {
      it("should have curent Iteration value to 0") {
        assert(initialWorld2.currentIteration==0)
      }
    }
    describe("with a given Environment") {
      it("should match the given Environment temperature") {
        assert(initialWorld2.temperature==initialWorld.temperature)
      }
    }

  }
}
