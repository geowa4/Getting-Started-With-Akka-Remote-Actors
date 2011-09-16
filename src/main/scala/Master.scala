import akka.actor._
import Actor._
import java.util.concurrent.CountDownLatch
import akka.routing._
import Routing._
import akka.dispatch.Future

object PiServer extends App {
  register
  
  def register {
    remote.start()
    remote.registerPerSession("pi-calculator-service", actorOf[Master])
  }
}

class Master extends Actor {
  
  var pi: Double = _
  var nrOfResults: Int = _
  var start: Long = _

  def receive = {
    case Calculate(numTerms) =>
      // collect the workers' responses in a list
      // so we can collect all their responses
      var futures:List[Future[Double]] = List()
      // schedule work
      for (i <- 0 until numTerms) {
        val worker = actorOf[Worker]
        worker.start
        futures :+= worker !!! Work(i * numTerms, numTerms)
        worker ! PoisonPill
      }
      // combine all the results when they come in
      futures.foreach {
        pi += _.get
      }
      self reply pi
}

  override def preStart() {
    start = System.currentTimeMillis
  }

  override def postStop() {
    println(
      "\n\tCalculation time: \t%s millis"
      .format(System.currentTimeMillis - start))
  }
}