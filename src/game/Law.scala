package game

/** A law of the quest that construction of QuestGroups must follow.
  *
  *  @constructor law
  *  @param name the quest iteration
  *  @param strict the group of participants for that quest
  *  @param statement :Statement of the law
  */
case class Law[T] (
  name:String,
  strict: Boolean,
  statement: Statement[T]
)
/**
  * This a factory of class Law
  */
  object Law{
  /** Method that  filters laws accord type they applied to
    * @param laws : list of laws to filter
    *  @return  A list of laws for type T
    */
    def filterLawsByType[T](laws: List[Law]): List[Law] ={
      laws.filter(law => law.statement[T])
    }
  /** Method thar evaluate a case of Type T
    @param caseType : case to Evaluate
    * @param laws : list of laws to filter
    *  @return  A boolean that means that case fill laws or doesnt.
    */
    def evaluateCase[T](caseType:T, laws: List[Law]):Boolean ={
      val lawsFiltered = filterLawsByType[T](laws)
      val evaluations = lawsFiltered.map(law =>
      law.statement.evaluate(caseType[T])
      )
      evaluations.foldLeft(true)(_ && _)
    }
  }
/** A statament of a law for a type T
  *  @constructor statement
  *  @param validation function from T to Boolean
  */
case class Statement[T](
  validation: (T =>Boolean)){

  /** Method evaluate  a casetype with the statement
    * @param caseType : list of laws to filter
    *  @return  A boolean result of statement to case
    */
  def evaluate(caseType:T):Boolean = validation(caseType)
}
