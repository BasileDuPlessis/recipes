import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import akkahttp.App.route
import akka.http.scaladsl.model.StatusCodes._
import org.scalatest.words.IncludeWord
import com.sksamuel.elastic4s.ElasticClient
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.common.settings.Settings
import org.slf4j

class AppSpec extends WordSpec with Matchers with ScalatestRouteTest with BeforeAndAfter {


  val tmpdir = System.getProperty("java.io.tmpdir")
  val settings = Settings.settingsBuilder()
    .put("path.home", tmpdir)
  val client = ElasticClient.local(settings.build)

  before {
    client.execute(
      index into "recipes" / "recipe" id 1 fields (
        "title" -> "chocolate cake"
        )
    ).await
  }


  "The service" should {

    "return a list of recipes for GET requests to the / path" in {
      // tests:
      Get("/hello") ~> route(client) ~> check {
        status shouldBe OK
        println(responseAs[String])
        responseAs[String] should include("chocolate cake")
      }
    }

  }


}