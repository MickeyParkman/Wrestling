public class Match{
   
   private int matchNumber;
   private int weightClass;
   public Wrestler wrestler1, wrestler2;
   private int winner; // consider making Wrestler type winner
   public boolean isConsolation;
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
      if(winner == -1)
         return "";
      else
         return wrestler1.name + " vs " + wrestler2.name;
   }
   
   public void printMatchUp(){
      if(!isConsolation)
         System.out.println(wrestler1.name + " vs " + wrestler2.name + " : The winner was " + winner + " **************");
      else
         System.out.println(wrestler1.name + " vs " + wrestler2.name + " : The winner was " + winner);
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
      int lastRound = (int) (Math.log(Tournament.wrestlersPerBracket) / Math.log(2)) * 2 - 3;
      int nextRound = 0;
      if(roundNum == 0 || isConsolation || roundNum == lastRound)
         nextRound = roundNum + 1;
      else
         nextRound = roundNum + 2;
      int matchesPerRound = matchesPerRound = (int)(Tournament.roundToMatchRatio[roundNum + 1] * Tournament.wrestlersPerBracket - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / Tournament.NUM_BRACKETS;
      int nextRoundStartNum = (int) (Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket);
      int startNum = (int) (Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket);
      int mNum = 0;//(int)startNum + ((matchNumber / matchesPerRound) % Tournament.NUM_BRACKETS * matchesPerRound + matchNumber % matchesPerRound / 2);      
      if(roundNum == 0){
         mNum = nextRoundStartNum + (int) (matchNumber - startNum) / matchesPerRound * matchesPerRound + matchNumber % matchesPerRound / 2; 
      }else if(roundNum == lastRound){
         mNum = nextRoundStartNum + matchNumber - startNum;
      }else if(!isConsolation){
         int temp1 = (int)(matchNumber - startNum) / matchesPerRound;
         int temp2 = (int) startNum + temp1 * matchesPerRound;
         mNum = nextRoundStartNum + temp1 * matchesPerRound / 2 + (int)(matchNumber - temp2)/2; 
      }else if(isConsolation && roundNum % 2 == 1){
         int temp1 = (int)(matchNumber - startNum) / matchesPerRound;
         int temp2 = (int) startNum + temp1 * matchesPerRound;
         mNum = nextRoundStartNum + temp1 * matchesPerRound / 2 + (int)(matchNumber - temp2 - matchesPerRound/2) % (matchesPerRound/2);
         //mNum = startNum + (int) (matchNumber - startNum) / matchesPerRound * matchesPerRound/2 + matchNumber % (matchesPerRound / 2);
      }else{
         int weight = (int) (matchNumber - startNum) / matchesPerRound;
         mNum = nextRoundStartNum +  weight * matchesPerRound + matchesPerRound / 2 + (matchNumber - startNum - weight * matchesPerRound) / 2;
      }
      Match nextMatch = Tournament.matches[mNum];
      if(roundNum == lastRound){
         nextMatch.setWrestler(0, winner);
      }else if(roundNum == 0 || !isConsolation || (isConsolation && roundNum % 2 == 0)){
         nextMatch.setWrestler(matchNumber % 2, winner);
      }else{
         nextMatch.setWrestler(1, winner);
      }
   }
   
   public void setNextMatchLoser(Wrestler loser){
      int lastRound = (int) (Math.log(Tournament.wrestlersPerBracket) / Math.log(2)) * 2 - 3;
      int nextRound = roundNum + 1;
      int matchesPerRound = (int)(Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket - Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket) / Tournament.NUM_BRACKETS;
      int nextRoundStartNum = (int) (Tournament.roundToMatchRatio[nextRound] * Tournament.wrestlersPerBracket);
      int startNum = (int) (Tournament.roundToMatchRatio[roundNum] * Tournament.wrestlersPerBracket);
      int weight = (matchNumber - startNum) / matchesPerRound;
      int mNum = 0;
      Match nextMatch;
      if(roundNum != lastRound)
      {                           
         if(roundNum == 0){            
            mNum = nextRoundStartNum + weight * matchesPerRound + matchesPerRound / 2 + (matchNumber - weight * matchesPerRound) / 2;
            nextMatch = Tournament.matches[mNum];
            nextMatch.setWrestler(matchNumber % 2, loser);            
         }else if(!isConsolation){
            if((roundNum + 1) % 4 != 0){
               int nextRoundWeightStart = weight * matchesPerRound / 2;
               mNum = nextRoundStartNum + nextRoundWeightStart + matchesPerRound / 2 - 1 - (matchNumber - weight * matchesPerRound) % (matchesPerRound / 2);
               nextMatch = Tournament.matches[mNum];
               nextMatch.setWrestler(0, loser);
            }else{
               int nextRoundWeightStart = weight * matchesPerRound / 2;
               mNum = nextRoundStartNum + nextRoundWeightStart + (matchNumber - weight * matchesPerRound) % (matchesPerRound / 2);
               nextMatch = Tournament.matches[mNum];
               nextMatch.setWrestler(0, loser);            
            }
         }
      }else{
         mNum = nextRoundStartNum + matchNumber - startNum;
         nextMatch = Tournament.matches[mNum];
         nextMatch.setWrestler(1, loser);
      }
   }
}

