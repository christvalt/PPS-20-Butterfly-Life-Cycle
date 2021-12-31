import org.scalatest._
import funspec._
import model.World
import model.common.Environment

class WordTest extends AnyFunSpec {


  val initialSettingEnvirinement : Environment = {
    Environment(
      temperature=2,
      buttefly=2,
      eggs= 3,
      puppa= 3,
      larva= 3,
      plant =2 ,
      predator=3,
      days=2
    )
  }
  val  initialWorld: World = World(initialSettingEnvirinement)

  val updatedWorld :World = World.updateState(initialWorld)

  describe("A  World") {
    describe("when initialized ") {
      it("should have curent Iteration value to 0") {
        assert(initialWorld.currentIteration==0)
      }
    }
    describe("with a given Environment") {
      it("should match the given Environment temperature") {
        assert(initialWorld.temperature==initialSettingEnvirinement.temperature)
      }
    }

  }


}
