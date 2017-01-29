package participants.types

/** A ParticipantPerformance
  *
  *  @constructor .
  */
object ParticipantPerformance {

  sealed abstract class ParticipantPerformance(val value: String) {
    override def toString: String = "Performance "+value
  }

  case object LowPerformance extends ParticipantPerformance(value = "Low")

  case object MediumPerformance extends ParticipantPerformance(value = "Medium")

  case object HighPerformance extends ParticipantPerformance(value = "High")

  def unapply(int: Int): Option[ParticipantPerformance] = int match {
    case 0 => Some(LowPerformance)
    case 1 => Some(MediumPerformance)
    case 2 => Some(HighPerformance)
    case _ => None
  }

}
