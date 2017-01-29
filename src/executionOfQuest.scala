import game.{Statement, Law, QuestGroup, Quest}
import participants.Poblation


class executionOfQuest {

}
object executionOfQuest extends App {

  // first we defined poblation
  var poblation = Poblation("AmericanPoblations",100,List.empty)
  poblation = poblation.fillPoblation()

  // in second place we defined the laws
  val condTribes = (questG: QuestGroup) => {
    val tribes = (questG.group.map(part => part.tribe) ).distinct
    (tribes.length == questG.group.length)
  }
  val statementTribes = Statement(validation = condTribes)
  val lawTribes:Law[QuestGroup] = Law("lawTribes",true,statementTribes)
  val condPerfomance = (questG: QuestGroup) => {
    val performances = (questG.group.map(part => part.performance) ).distinct
    (performances.length == questG.group.length)
  }
  val statementPerformance = Statement(validation = condPerfomance)
  val lawPerformance:Law[QuestGroup] = Law("lawPerformance",true,statementPerformance)
  var quest = Quest(poblation,List(lawPerformance,lawTribes),7,List.empty)
}
