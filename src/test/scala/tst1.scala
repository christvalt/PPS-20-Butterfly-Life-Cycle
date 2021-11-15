import org.scalatest._
import funspec._
import model.reaction.EatingEffect.collisionREceptivePLan

class tst1 extends AnyFunSpec {

  describe("A Set") {
    describe("when empty") {
      it("should have size 0") {
        assert(Set.empty.size == 0)
      }

      it("should produce NoSuchElementException when head is invoked") {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
    }

      //assert(collisionREceptivePLan().exists(_.isEmpty))
  }
}
