public class Match{
   
   private int matchNumber;
   private int weightClass;
   private Wrestler wrestler1, wrestler2;
   private int winner; // consider making Wrestler type winner
   private boolean isConsolation;
   
   //stores the information on how the wrestler won. ie pin 1:23
   private String winStatement;
   
   public Match(int matchNumber){
      this.winner = -1;
      this.matchNumber = matchNumber;
   }
   
   //public void setWinner(int winner){
     // this.winner = winner;
   //}
   
   public void setWrestler(int wrestlerNum, Wrestler wrestler){
      if(wrestlerNum == 0){
         wrestler1 = wrestler;
      }else{
         wrestler2 = wrestler;
      }
   }
   
   public Wrestler getWinner(){
      if(winner == 0)
         return wrestler1;
      else if(winner == 1)
         return wrestler2;
      else   
         return null;
   }
   
   public void setWinStatement(String statement){
      this.winStatement = statement;
   }
   
   public String toString(){
      if(winner == 0)
         return "";
      else
         return getWinner().name + " " + winStatement;
   }
   
   public void printMatchUp(){
      System.out.println(wrestler1.name + " vs " + wrestler2.name);
   }
   
   public void updateMatch(int winType, String winCondition) {
      winner = winType / 10;
      //int offset = weightClass * Tournament.getNumMatches();
      //int matchIndex = matchNumber - weightClass;
      Wrestler matchWinner;
      if (winner == 0){
         matchWinner = wrestler1;
         wrestler2.byePoints = 0; // bye points are reset for loser
      } else {
         matchWinner = wrestler2;
         wrestler1.byePoints = 0; 
      }
      int winCond = winType % 10;
      int scoreAdd;
      switch(winCond) {
         case 0:  winStatement = "ff";
                  scoreAdd = matchWinner.byePoints + 6;
                  matchWinner.addTournamentScore(scoreAdd);
                  matchWinner.byePoints = 0;
                  break;
         case 1:  winStatement = "Bye";
                  scoreAdd = 0;
                  matchWinner.byePoints += 6;
                  break;
         case 2:  winStatement = "Injury";
                  scoreAdd = matchWinner.byePoints + 6;
                  matchWinner.addTournamentScore(scoreAdd);
                  matchWinner.byePoints = 0;
                  break;
         case 3:  winStatement = "Decision, " + winCondition;
                  scoreAdd = matchWinner.byePoints + 3;
                  matchWinner.addTournamentScore(scoreAdd);
                  matchWinner.byePoints = 0;
                  break;
         case 4:  winStatement = "Major Decision, " + winCondition;
                  scoreAdd = matchWinner.byePoints + 4;
                  matchWinner.addTournamentScore(scoreAdd);
                  matchWinner.byePoints = 0;
                  break;
         case 5:  winStatement = "Tech, " + winCondition;
                  scoreAdd = matchWinner.byePoints + 5;
                  matchWinner.addTournamentScore(scoreAdd);
                  matchWinner.byePoints = 0;
                  break;
         case 6:  winStatement = "Pin, time: " + winCondition;
                  scoreAdd = matchWinner.byePoints + 6;
                  matchWinner.addTournamentScore(scoreAdd);
                  matchWinner.byePoints = 0;
                  break;
      }
      if (matchWinner.isScorer) {
         // teamScore.add(scoreAdd);
      }
      // first offset = numWrestlers 
   }
}

