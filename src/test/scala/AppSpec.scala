import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import akkahttp.App.route
import akka.http.scaladsl.model.StatusCodes._
import org.scalatest.words.IncludeWord

class AppSpec extends WordSpec with Matchers with ScalatestRouteTest {

  "The service" should {

    "return a greeting for GET requests to the /hello path" in {
      // tests:
      Get("/hello") ~> route ~> check {
        status shouldBe OK
        responseAs[String] should include("hello")
      }
    }

  }
}