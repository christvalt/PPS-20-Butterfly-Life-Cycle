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
      plant =4 ,
      predator=7,
      days=200
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
        assert(initialWorld.totalIterations==initialSettingEnvirinement.days*100)
        assert(initialWorld.currentIteration==0)
        assert(Environment(2, 2,3,3,4,2,3,200).temperature == 2)
        assert(Environment(2, 2,3,3,4,2,3,200).buttefly == 2)
        assert(Environment(2, 2,3,3,4,2,3,200).eggs == 3)
        assert(Environment(2, 2,3,3,4,2,3,200).puppa == 3)
        assert(Environment(2, 2,3,3,3,2,3,200).larva == 3)
        assert(Environment(2, 2,3,3,4,4,3,200).plant == 4)
        assert(Environment(2, 2,3,3,4,2,7,200).days== 200)
      }


    }

  }


}
