package model.creature

import alice.tuprolog.{Prolog, SolveInfo, Term, Theory}

object PrologEngine {


  private val engine = new Prolog()
  engine.setTheory(new Theory(getClass.getResource("/movementTheory.pl").openStream()))



  def engine(goal : Term): Iterable[SolveInfo] = {
    new Iterable[SolveInfo]{
      override def iterator: Iterator[SolveInfo] = new Iterator[SolveInfo]{
        var solution: Option[SolveInfo] = Some(engine.solve(goal))

        override def hasNext: Boolean = solution.isDefined && (solution.get.isSuccess || solution.get.hasOpenAlternatives)

        override def next(): SolveInfo = {
          try solution.get
          finally solution = if (solution.get.hasOpenAlternatives) Some(engine.solveNext()) else None
        }
      }
    }
  }
}
