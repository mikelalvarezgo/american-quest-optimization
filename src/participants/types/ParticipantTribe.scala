package participants.types

/** A ParticipantTribe
  *
  *  @constructor ParticipantTribe.
  */
object ParticipantTribe {

  sealed abstract class ParticipantTribe(val value: Int) {
    override def toString: String = value.toString()
  }

  case object Tribe1 extends ParticipantTribe(value = 1)

  case object Tribe2 extends ParticipantTribe(value = 2)

  case object Tribe3 extends ParticipantTribe(value = 3)

  case object Tribe4 extends ParticipantTribe(value = 4)

  case object Tribe5 extends ParticipantTribe(value = 5)

  case object Tribe6 extends ParticipantTribe(value = 6)

  case object Tribe7 extends ParticipantTribe(value = 7)

  def unapply(int: Int): Option[ParticipantTribe] = int match {
    case 0 => Some(Tribe1)
    case 1 => Some(Tribe2)
    case 2 => Some(Tribe3)
    case 3 => Some(Tribe4)
    case 4 => Some(Tribe5)
    case 5 => Some(Tribe6)
    case 6 => Some(Tribe7)
    case _ => None
  }
}


