public class Bracket {

   private Wrestler[] wrestlers;
   int bracketNum;
   int numWrestlers;
   
   public Bracket(int bracketNum, int numWrestlers) {
      this.numWrestlers = numWrestlers;
      this.bracketNum = bracketNum;
      wrestlers = new Wrestler[numWrestlers];
      int numRounds = (int) (Math.log(numWrestlers) / Math.log(2));      
   } 
}