package akkahttp

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.sksamuel.elastic4s.{ElasticClient, RichSearchResponse}
import scala.concurrent.Future
import scala.io.StdIn

object App {

  def queryLastRecipes(client:ElasticClient): RichSearchResponse = {
    import com.sksamuel.elastic4s.ElasticDsl._
    client.execute(search in "recipes" / "recipe").await
  }

  def route(client:ElasticClient) = {
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`,
          queryLastRecipes(client).original.getHits.hits.map(_.sourceAsString()).mkString("")
        ))
      }
    }
  }

  def main(args: Array[String]) {
/*
    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ ⇒ system.terminate()) // and shutdown when done
*/
  }

}