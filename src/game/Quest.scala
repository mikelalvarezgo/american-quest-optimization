package game

import participants.{Participant, Poblation}

/** A group of participants of the group in a quest.
  *
  *  @constructor group of participants of the group.
  *  @param iteration the quest iteration
  *  @param group the group of participants for that quest
  */
case class QuestGroup(
  iteration: Int,
  group: List[Participant]
){
  def isCompleted:Boolean  = group.length == 3
}

/** A group of participants of the group in a quest.
  *
  *  @constructor group of participants of the group.
  *  @param poblation the quest iteration
  *  @param laws the group of participants for that quest
  *  @param nQuest the group of participants for that quest
  *  @param groups the group of participants for that quest

  */
case class Quest(
  poblation: Poblation,
  laws: List[Law],
  nQuest: Int,
  groups: List[Map[Int , List[QuestGroup]]]
) {

  /** Method that create the group for the quest
    *  @return  . a new Quest with updated poblation and groups
    */
  def createGroups():Quest = {
    var nPob = poblation
    val results =(1 to nQuest).map(i => {
        (groups,nPob) =  createGroupForQuest(i,nPob)
      groups
     })
    this.copy(groups = results.toList.flatten,poblation=nPob )
  }

  /** Method that create the group for the quest
    *  @return  list of participants not uses in any group
    */
  def findUnassignedParticipant(listAssigned:List[Int]):List[Participant]={
    poblation.participants.filter(p=> !listAssigned.contains(p._id))
  }

  /** Method that find a valid participant for a groups
    * @param currentQuestGroup: current group fro the quest
    * @param listAssigned: list of assignated participants
    *  @return enter parameters updated
    */
  def findValidParticipant(
    currentQuestGroup: QuestGroup,
    listAssigned: List[Int]): (QuestGroup,List[Int]) = {
      val freeHabitants = findUnassignedParticipant(listAssigned)
      for(freeHab <- freeHabitants){
        var newGroup = currentQuestGroup.group.::(freeHab)
        var lawsOfGroup = Law.filterLawsByType[List[Participant]](laws)
        if(Law.evaluateCase[List[Participant]](newGroup,lawsOfGroup)){
          return (currentQuestGroup.copy(group = newGroup),listAssigned.::(freeHab._id))
        }
      }
    println("NOT FOUND PARTICIPANT TO THIS GROUP " +currentQuestGroup)
    (currentQuestGroup,listAssigned)
  }
  /** Method that update poblation with interactions
    * @param currentQuestGroup: current group fro the quest
    *  @return poblation updated with interactions
    */
  def addCooperationHistory(currentQuestGroup: QuestGroup): Poblation = {
    var currentPoblation = poblation
    currentQuestGroup.group.map(part => {
      part.interactions.++(currentQuestGroup.group.map
        (parti =>{if (parti._id !=part._id) parti._id }))
      currentPoblation: Poblation =
        currentPoblation.copy(participants =
           ((currentPoblation.participants.filterNot(p => p._id==part._id) ).::(part)))
    })
    currentPoblation
  }

  /** Method create group for an iterarion of the Quest
    * @param nQuest: number of iterations
    * @param poblationForQuest: poblation for the quest
    *  @return  List of questGroups and poblation updated with interactions
    */
  def createGroupForQuest(nQuest:Int, poblationForQuest:Poblation):
  (Map[Int , List[QuestGroup]],Poblation) = {

    var listOfGroups:List[QuestGroup] = List.empty
    var listOfAssigned:List[Int]= List.empty // list of ids of participants assigned
    var currentPoblation = poblationForQuest
    var currentQuestGroup = QuestGroup(nQuest,List.empty)
    while(listOfAssigned.length != poblation.size){
      (currentQuestGroup,listOfAssigned) =
        findValidParticipant(currentQuestGroup,listOfAssigned)
      if(currentQuestGroup.isCompleted){
        currentPoblation = addCooperationHistory(currentQuestGroup)
        listOfGroups = listOfGroups.::( currentQuestGroup)
        currentQuestGroup =  QuestGroup(nQuest,List.empty)
      }
    }
    (Map(nQuest -> listOfGroups),currentPoblation)
  }
}
