public class Bracket {

   private Wrestler[] wrestlers;
   int bracketNum;
   int numWrestlers;
   
   public Bracket(int bracketNum, Wrestler[] wrestlers) {
      this.numWrestlers = wrestlers.length;
      this.bracketNum = bracketNum;
      this.wrestlers = wrestlers;
      int numRounds = (int) (Math.log(numWrestlers) / Math.log(2));
      int startIndex = bracketNum * wrestlers.length;
      for(int i = 0; i < wrestlers.length / 2; i++){
         Tournament.matches[startIndex + i].setWrestler(0, wrestlers[i]);
         Tournament.matches[startIndex + i].setWrestler(1, wrestlers[wrestlers.length - 1 - i]);
         Tournament.matches[startIndex + i].printMatchUp();
      }      
   } 
}