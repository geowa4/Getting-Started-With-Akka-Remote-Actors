sealed trait PiMessage

case class Calculate(numTerms: Int) extends PiMessage

case class Work(start: Int, numTerms: Int) extends PiMessage
