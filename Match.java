public class Match{
   
   private int matchNumber;
   private int weightClass;
   private Wrestler wrestler1, wrestler2;
   private int winner; // consider making Wrestler type winner
   private boolean isConsolation;
   private int roundNum;
   //stores the information on how the wrestler won. ie pin 1:23
   private String winStatement;
   
   public Match(int matchNumber, boolean isConsolation, int roundNum){
      this.winner = -1;
      this.matchNumber = matchNumber;
      this.isConsolation = isConsolation;
      this.roundNum = roundNum;
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
         setNextMatchWinner(wrestler1);
         setNextMatchLoser(wrestler2);
         wrestler2.byePoints = 0; // bye points are reset for loser
      } else {
         matchWinner = wrestler2;
         setNextMatchWinner(wrestler2);
         setNextMatchLoser(wrestler1);
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
   
   public void setNextMatchWinner(Wrestler winner){
      int lastRound = (int) (Math.log(Tournament.wrestlersPerBracket) / Math.log(2)) * 2 - 2;
      int nextRound = 0;
      if(roundNum == 0)
         nextRound = 1;
      else if(isConsolation)
         nextRound = roundNum + 1;
      else
         nextRound = roundNum + 2;
      int matchesPerRound = matchesPerRound = (int)(Tournament.roundToMatchRatio[roundNum + 1] * Tournament.wrestlersPerBracket - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / Tournament.NUM_BRACKETS;
      int startNum = (int) (Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket);
      int mNum = 0;//(int)startNum + ((matchNumber / matchesPerRound) % Tournament.NUM_BRACKETS * matchesPerRound + matchNumber % matchesPerRound / 2);      
      if(roundNum == 0)
         mNum = startNum + (int) (matchNumber - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / matchesPerRound * matchesPerRound + matchNumber % matchesPerRound / 2; 
      else if(!isConsolation){
         int temp1 = (int)(matchNumber - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / matchesPerRound;
         mNum = startNum + (int) ((int)(matchNumber - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / matchesPerRound) * matchesPerRound/2 + matchNumber % (matchesPerRound / 2); 
      }else if(isConsolation && roundNum % 2 == 1){
         mNum = startNum + (int) (matchNumber - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / matchesPerRound * matchesPerRound/2 + matchNumber % (matchesPerRound / 2);
      }else{
         mNum = startNum + (int) (matchNumber - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / matchesPerRound * 2;
      }
      Match nextMatch = Tournament.matches[mNum];
      if(roundNum == 0 || !isConsolation || (isConsolation && roundNum % 2 == 0)){
         nextMatch.setWrestler(matchNumber % 2, winner);
      }else{
         nextMatch.setWrestler(1, winner);
      }
   }
   
   public void setNextMatchLoser(Wrestler loser){
      int lastRound = (int) (Math.log(Tournament.wrestlersPerBracket) / Math.log(2)) * 2 - 2;
      int nextRound = roundNum + 1;
      int matchesPerRound = (int)(Tournament.roundToMatchRatio[nextRound + 1] * Tournament.wrestlersPerBracket - Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket) / Tournament.NUM_BRACKETS;
      /*if(roundNum == 0){
         int mNum = (int) (Tournament.roundToMatchRatio[1] * Tournament.wrestlersPerBracket + (((int)(matchNumber / matchesPerRound + 1) * matchesPerRound) - 1 - matchNumber % matchesPerRound / 2));
         Match nextMatch = Tournament.matches[mNum];
         nextMatch.setWrestler(0, loser);
      }else*/ 
      if(roundNum != lastRound){
         int mNum = (int) (Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket + (((matchNumber / matchesPerRound) % Tournament.NUM_BRACKETS + 1) * matchesPerRound - 1 - matchNumber % matchesPerRound / 2));
         Match nextMatch = Tournament.matches[mNum];
         if(roundNum == 0){
            nextMatch.setWrestler((matchNumber + 1) % 2, loser);
         }else if(!isConsolation){
            int temp1 = (((int)(matchNumber - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / matchesPerRound * 2) % Tournament.NUM_BRACKETS + 1);
            int temp2 = matchNumber % (matchesPerRound / 2);            
            mNum = (int) (Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket + (((matchNumber / matchesPerRound) % Tournament.NUM_BRACKETS + 1) * matchesPerRound - 1 - matchNumber % (matchesPerRound / 2)));
            nextMatch.setWrestler(0, loser);
         }
      }
   }
}

