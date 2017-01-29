package participants

import participants.types.{ParticipantTribe, ParticipantPerformance}
import scala.collection.mutable.ListBuffer

/** A Participant in the quest.
  *
  *  @constructor Poblation.
  *  @param name of the poblation
  *  @param size number of habitants
  *  @param participants List of participants of the poblations
  */
case class Poblation(
  name: String,
  size: Int,
  participants: List[Participant]){

  /** Method that fill the poblation
    *  @return  .A Poblation with habitants
    */
  def fillPoblation():Poblation = {
    val random = scala.util.Random
    var initPerf = random.nextInt(7)
    var initTribe= random.nextInt(3)
    var participantsBuffer = new ListBuffer[Participant]()
    for(n <- 1 to size){
      initPerf += 1
      initTribe += 1
      val part =Participant(
        n,
        ParticipantTribe.unapply(initTribe %7).get,
        ParticipantPerformance.unapply(initPerf % 3).get,
        List.empty)
      participantsBuffer += part
    }
    this.copy(participants = participantsBuffer.toList)
  }
}
