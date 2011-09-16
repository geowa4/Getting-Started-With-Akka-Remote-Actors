import akka.actor.{Actor, PoisonPill}
import Actor._
import akka.routing.{Routing, CyclicIterator}
import Routing._
import akka.dispatch.Dispatchers

import java.util.concurrent.CountDownLatch

object PiClient extends App {

  calculate(nrOfWorkers = 4, nrOfElements = 10000, nrOfMessages = 10000)

  def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {
    // retrieve the master
    val master = remote.actorFor("pi-calculator-service", "localhost", 2552)

    // start the calculation and get back a future
    val result = master !!! Calculate(10000)

    println("\n\tPi estimate: \t%s".format(result.get))
  }
}
