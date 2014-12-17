
public class Tournament{

   public Bracket[] brackets;
   public static Match[] matches;
   private int wrestlersPerBracket = 8;
   
   
   public Tournament(){
      brackets = new Bracket[13];
      matches = new Match[getNumMatches(brackets.length)];
      for(int i = 0; i < brackets.length; i++)
         brackets[i] = new Bracket(i, wrestlersPerBracket);
      
      for(int i = 0; i < matches.length; i++)
         matches[i] = new Match(i);
      System.out.println();
   }
   
   public static void main(String[] args){
      new Tournament();
   }
   
   private int getNumMatches(int numBrackets){
      int total = 0;
      int roundNum = 0;
      int currRound = wrestlersPerBracket * numBrackets / 2;
      while(currRound != 2 * numBrackets || roundNum == 0){
         if(roundNum > 1)
            currRound /= 2;
         total += currRound;
         roundNum++;
      }
      return total;
   }

}