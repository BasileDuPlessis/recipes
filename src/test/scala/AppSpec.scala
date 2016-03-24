import collection.mutable.Stack
import org.scalatest._

class AppSpec extends FlatSpec with Matchers {

  "Multiplication by 2" should "return same result as addition" in {
    val number = 2
    number * 2 should be (number + number)
  }

}