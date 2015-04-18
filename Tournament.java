
public class Tournament{

   public Bracket[] brackets;
   public static Match[] matches;
   public static double[] roundToMatchRatio = {0, 7, 14, 17.5, 21, 22.75, 24.5, 25.375}; 
   public static int wrestlersPerBracket;
   public static final int NUM_BRACKETS = 14;
   
   //take in a 2d array of wrestlers. outer array is weight classes, inner array is wrestlers
   //The wrestlers will already be sorted by seed
   public Tournament(Wrestler[][] wrestlers, int numWrestlers){
      brackets = new Bracket[14];
      wrestlersPerBracket = numWrestlers;
      //Find the total number of matches and then add an extra "match" to hold the places of the winners and stuff
      matches = new Match[getNumMatches() * NUM_BRACKETS + (/*wrestlersPerBracket / 8 + 1*/2) * NUM_BRACKETS];
      for(int i = 0; i < brackets.length; i++)
         brackets[i] = new Bracket(i, wrestlersPerBracket);
      int indexNum = 0;
      int roundNum = 0;
      int lastRound = (int) (Math.log(wrestlersPerBracket) / Math.log(2)) * 2 - 2;
      
      for(int i = 0 ; i < NUM_BRACKETS * wrestlersPerBracket / 2; i++) {
         matches[i] = new Match(indexNum, false, roundNum);
         brackets[i / (wrestlersPerBracket / 2)].addMatch(matches[indexNum]);
         indexNum++;
      }
      roundNum++;
      int matchesPerRound = wrestlersPerBracket / 2;
      while(indexNum < matches.length) {
         for(int i = 0; i < NUM_BRACKETS; i++) { // do this for all weight classes
            for(int j = 0; j < matchesPerRound / 2; j++) {
               matches[indexNum] = new Match(indexNum, false, roundNum); // next championship bracket
               brackets[i].addMatch(matches[indexNum]);
               indexNum++;               
            }
            for(int j = 0; j < matchesPerRound / 2; j++) {
               matches[indexNum] = new Match(indexNum, true, roundNum); // next consolation
               brackets[i].addMatch(matches[indexNum]);
               indexNum++;
            }
         }
         roundNum++;
         if(roundNum != lastRound)
            matchesPerRound /= 2;
         if(indexNum < matches.length - (wrestlersPerBracket / 8 + 1) * NUM_BRACKETS){
            for(int i = 0; i < NUM_BRACKETS; i++) {
               for(int j = 0; j < matchesPerRound; j++) { // consolation only round
                  matches[indexNum] = new Match(indexNum, true, roundNum);
                  brackets[i].addMatch(matches[indexNum]);
                  indexNum++;
               }
            }
            roundNum++;            
         }else{
            for(int i = 0; i < NUM_BRACKETS; i ++){
               matches[indexNum] = new Match(indexNum, false, roundNum);
               brackets[i].addMatch(matches[indexNum]);
               matches[indexNum + 1] = new Match(indexNum, true, roundNum);
               brackets[i].addMatch(matches[indexNum + 1]);
               indexNum += 2;
            }
         }
      }
           
      brackets[2].showChampionship();
                  
      for(int i = 0; i < NUM_BRACKETS; i++){
         int startIndex = i * wrestlers[i].length / 2;
         for(int j = 0; j < wrestlers[i].length / 2; j++){
            Tournament.matches[startIndex + j].setWrestler(0, wrestlers[i][j]);
            Tournament.matches[startIndex + j].setWrestler(1, wrestlers[i][wrestlers[i].length - 1 - j]);
         }      
      } 
      
      for(int i = 0; i < matches.length - 28; i++){
         matches[i].updateMatch(6, " 1:00");
      }   
   }
   
   // returns the number of matches in each bracket
   public static int getNumMatches(){
      int total = 0;
      int roundNum = 0;
      int lastRound = (int) (Math.log(wrestlersPerBracket) / Math.log(2)) * 2 - 2;
      int currRound = wrestlersPerBracket;
      while(roundNum < lastRound || roundNum == 0){
         if(roundNum % 2 == 0)
            currRound /= 2;
         total += currRound;
         roundNum++;
      }
      return total;
   }

   
   public void initTestArrays(Wrestler[][] temp)
   {
   
   }
}