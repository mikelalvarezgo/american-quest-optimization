package participants

import participants.types.ParticipantPerformance._
import participants.types.ParticipantTribe._

/** A Participant in the quest.
  *
  *  @constructor participant.
  *  @param _id id of Participant
  *  @param tribe tribe which belongs participant
  *  @param performance type of performance of the participant
  *  @param interactions interactions with other participants in the quest
  */
case class Participant (
  _id: Int,
  tribe: ParticipantTribe,
  performance: ParticipantPerformance,
  interactions: List[Int]
 )

