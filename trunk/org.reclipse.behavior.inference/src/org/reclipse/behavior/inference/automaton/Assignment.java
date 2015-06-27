package org.reclipse.behavior.inference.automaton;


public class Assignment
{

   private String leftSide;

   private String rightSide;

   private String id;

   private AbstractSymbol symbol;


   public AbstractSymbol getSymbol()
   {
      return symbol;
   }


   public void setSymbol(AbstractSymbol symbol)
   {
      this.symbol = symbol;
   }


   public String getId()
   {
      return id;
   }


   public void setId(int id)
   {
      this.id = "assignment" + id;
   }


   public String getLeftSide()
   {
      return leftSide;
   }


   public void setLeftSide(String leftSide)
   {
      this.leftSide = leftSide;
   }


   public String getRightSide()
   {
      return rightSide;
   }


   public void setRightSide(String rightSide)
   {
      this.rightSide = rightSide;
   }


   /**
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return getLeftSide() + ":=" + getRightSide();
   }


   public void removeYou()
   {
      AbstractAutomaton tmpAutomaton = this.getAutomaton();
      if (tmpAutomaton != null)
      {
         this.setAutomaton(null);
      }

      AbstractSymbol tmpSymbol = this.getSymbol();
      if (tmpSymbol != null)
      {
         this.setSymbol(null);
      }
   }

   private AbstractAutomaton automaton;


   public AbstractAutomaton getAutomaton()
   {
      return this.automaton;
   }


   public boolean setAutomaton(AbstractAutomaton value)
   {
      boolean changed = false;
      if (this.automaton != value)
      {
         AbstractAutomaton oldValue = this.automaton;

         if (this.automaton != null)
         {
            this.automaton = null;
            oldValue.removeFromAssignments(this);
         }
         this.automaton = value;
         if (value != null)
         {
            value.addToAssignments(this);
         }
         changed = true;

      }
      return changed;
   }

}
