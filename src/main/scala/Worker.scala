import akka.actor._

class Worker extends Actor {
  // make a new actor each time. i know it's not ideal for this problem, but i'm learning
  self.id = newUuid.toString
  
  def receive = {
    case Work(start, numTerms) =>
      self.reply(calculatePiFor(start, numTerms)) // perform the work
  }
  
  def calculatePiFor(start: Int, numTerms: Int): Double = {
    var acc = 0.0
    for (i <- start until (start + numTerms))
      acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1)
    acc
  }
}
