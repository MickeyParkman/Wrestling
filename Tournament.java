
public class Tournament{

   public Bracket[] brackets;
   public static Match[] matches;
   public static double[] roundToMatchRatio = {0, 7, 14, 17.5, 21, 22.75}; 
   public static int wrestlersPerBracket = 8;
   public static final int NUM_BRACKETS = 14;
   
   //take in a 2d array of wrestlers. outer array is weight classes, inner array is wrestlers
   //The wrestlers will already be sorted by seed
   public Tournament(Wrestler[][] wrestlers){
      brackets = new Bracket[14];
      
      //Find the total number of matches and then add an extra "match" to hold the places of the winners and stuff
      matches = new Match[getNumMatches() * NUM_BRACKETS + (wrestlersPerBracket / 8 + 1) * NUM_BRACKETS];
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
         if(indexNum < matches.length){
            for(int i = 0; i < NUM_BRACKETS; i++) {
               for(int j = 0; j < matchesPerRound; j++) { // consolation only round
                  matches[indexNum] = new Match(indexNum, true, roundNum);
                  brackets[i].addMatch(matches[indexNum]);
                  indexNum++;
               }
            }
            roundNum++;            
         }
      }
           
      brackets[1].showBracket();
                  
      for(int i = 0; i < NUM_BRACKETS; i++){
         System.out.println("Weight " + i);
         int startIndex = i * wrestlers[i].length / 2;
         for(int j = 0; j < wrestlers[i].length / 2; j++){
            Tournament.matches[startIndex + j].setWrestler(0, wrestlers[i][j]);
            Tournament.matches[startIndex + j].setWrestler(1, wrestlers[i][wrestlers[i].length - 1 - j]);
            Tournament.matches[startIndex + j].printMatchUp();
         }      
         System.out.println();
      } 
      
      for(int i = 0; i < matches.length - 28; i++){
         matches[i].updateMatch(6, " 1:00");
      }  
      
      for(Match m : matches)
         m.printMatchUp();   
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

   
   public void initTestArrays(Wrestler[][] temp){
      /*temp[0][0] = new Wrestler("Michael", 106, true, 12, 1);
      temp[0][1] = new Wrestler("David", 106, true, 12, 2);
      temp[0][2] = new Wrestler("Gordan", 106, true, 12, 3);
      temp[0][3] = new Wrestler("Daniel", 106, true, 12, 4);
      temp[0][4] = new Wrestler("Jason", 106, true, 12, 5);
      temp[0][5] = new Wrestler("Andrew", 106, true, 12, 6);
      temp[0][6] = new Wrestler("Shane", 106, true, 12, 7);
      temp[0][7] = new Wrestler("Ben", 106, true, 12, 8);
      
      temp[1][0] = new Wrestler("Blake", 113, true, 12, 1);
      temp[1][1] = new Wrestler("David", 113, true, 12, 2);
      temp[1][2] = new Wrestler("Gordan", 113, true, 12, 3);
      temp[1][3] = new Wrestler("Daniel", 113, true, 12, 4);
      temp[1][4] = new Wrestler("Jason", 113, true, 12, 5);
      temp[1][5] = new Wrestler("Andrew", 113, true, 12, 6);
      temp[1][6] = new Wrestler("Shane", 113, true, 12, 7);
      temp[1][7] = new Wrestler("Ben", 113, true, 12, 8);
      
      temp[2][0] = new Wrestler("Michael", 120, true, 12, 1);
      temp[2][1] = new Wrestler("David", 120, true, 12, 2);
      temp[2][2] = new Wrestler("Gordan", 120, true, 12, 3);
      temp[2][3] = new Wrestler("Robert", 120, true, 12, 4);
      temp[2][4] = new Wrestler("Jason", 120, true, 12, 5);
      temp[2][5] = new Wrestler("Andrew", 120, true, 12, 6);
      temp[2][6] = new Wrestler("Caleb", 120, true, 12, 7);
      temp[2][7] = new Wrestler("Ben", 120, true, 12, 8);
      
      temp[3][0] = new Wrestler("Michael", 126, true, 12, 1);
      temp[3][1] = new Wrestler("David", 126, true, 12, 2);
      temp[3][2] = new Wrestler("Gordan", 126, true, 12, 3);
      temp[3][3] = new Wrestler("Daniel", 126, true, 12, 4);
      temp[3][4] = new Wrestler("Jason", 126, true, 12, 5);
      temp[3][5] = new Wrestler("Andrew", 126, true, 12, 6);
      temp[3][6] = new Wrestler("Shane", 126, true, 12, 7);
      temp[3][7] = new Wrestler("Ben", 126, true, 12, 8);
      
      temp[4][0] = new Wrestler("Michael", 132, true, 12, 1);
      temp[4][1] = new Wrestler("David", 132, true, 12, 2);
      temp[4][2] = new Wrestler("Gordan", 132, true, 12, 3);
      temp[4][3] = new Wrestler("Daniel", 132, true, 12, 4);
      temp[4][4] = new Wrestler("Jason", 132, true, 12, 5);
      temp[4][5] = new Wrestler("Andrew", 132, true, 12, 6);
      temp[4][6] = new Wrestler("Shane", 132, true, 12, 7);
      temp[4][7] = new Wrestler("Ben", 132, true, 12, 8);
      
      temp[5][0] = new Wrestler("Brennan", 138, true, 12, 1);
      temp[5][1] = new Wrestler("David", 138, true, 12, 2);
      temp[5][2] = new Wrestler("Gordan", 138, true, 12, 3);
      temp[5][3] = new Wrestler("Daniel", 138, true, 12, 4);
      temp[5][4] = new Wrestler("Isaac", 138, true, 12, 5);
      temp[5][5] = new Wrestler("Andrew", 138, true, 12, 6);
      temp[5][6] = new Wrestler("Shane", 138, true, 12, 7);
      temp[5][7] = new Wrestler("Ben", 138, true, 12, 8);
      
      temp[6][0] = new Wrestler("Michael", 145, true, 12, 1);
      temp[6][1] = new Wrestler("David", 145, true, 12, 2);
      temp[6][2] = new Wrestler("Gordan", 145, true, 12, 3);
      temp[6][3] = new Wrestler("Daniel", 145, true, 12, 4);
      temp[6][4] = new Wrestler("Jason", 145, true, 12, 5);
      temp[6][5] = new Wrestler("Andrew", 145, true, 12, 6);
      temp[6][6] = new Wrestler("Shane", 145, true, 12, 7);
      temp[6][7] = new Wrestler("Ben", 145, true, 12, 8);
      
      temp[7][0] = new Wrestler("Michael", 152, true, 12, 1);
      temp[7][1] = new Wrestler("David", 152, true, 12, 2);
      temp[7][2] = new Wrestler("Gordan", 152, true, 12, 3);
      temp[7][3] = new Wrestler("Daniel", 152, true, 12, 4);
      temp[7][4] = new Wrestler("Jason", 152, true, 12, 5);
      temp[7][5] = new Wrestler("Andrew", 152, true, 12, 6);
      temp[7][6] = new Wrestler("Shane", 152, true, 12, 7);
      temp[7][7] = new Wrestler("Ben", 152, true, 12, 8);
      
      temp[8][0] = new Wrestler("Michael", 160, true, 12, 1);
      temp[8][1] = new Wrestler("David", 160, true, 12, 2);
      temp[8][2] = new Wrestler("Gordan", 160, true, 12, 3);
      temp[8][3] = new Wrestler("Daniel", 160, true, 12, 4);
      temp[8][4] = new Wrestler("Jason", 160, true, 12, 5);
      temp[8][5] = new Wrestler("Andrew", 160, true, 12, 6);
      temp[8][6] = new Wrestler("Shane", 160, true, 12, 7);
      temp[8][7] = new Wrestler("Ben", 160, true, 12, 8);
      
      temp[9][0] = new Wrestler("Michael", 170, true, 12, 1);
      temp[9][1] = new Wrestler("David", 170, true, 12, 2);
      temp[9][2] = new Wrestler("Gordan", 170, true, 12, 3);
      temp[9][3] = new Wrestler("Daniel", 170, true, 12, 4);
      temp[9][4] = new Wrestler("Jason", 170, true, 12, 5);
      temp[9][5] = new Wrestler("Andrew", 170, true, 12, 6);
      temp[9][6] = new Wrestler("Shane", 170, true, 12, 7);
      temp[9][7] = new Wrestler("Ben", 170, true, 12, 8);
      
      temp[10][0] = new Wrestler("Michael", 182, true, 12, 1);
      temp[10][1] = new Wrestler("David", 182, true, 12, 2);
      temp[10][2] = new Wrestler("Gordan", 182, true, 12, 3);
      temp[10][3] = new Wrestler("Daniel", 182, true, 12, 4);
      temp[10][4] = new Wrestler("Jason", 182, true, 12, 5);
      temp[10][5] = new Wrestler("Andrew", 182, true, 12, 6);
      temp[10][6] = new Wrestler("Shane", 182, true, 12, 7);
      temp[10][7] = new Wrestler("Ben", 182, true, 12, 8);
      
      temp[11][0] = new Wrestler("Michael", 195, true, 12, 1);
      temp[11][1] = new Wrestler("David", 195, true, 12, 2);
      temp[11][2] = new Wrestler("Gordan", 195, true, 12, 3);
      temp[11][3] = new Wrestler("Daniel", 195, true, 12, 4);
      temp[11][4] = new Wrestler("Jason", 195, true, 12, 5);
      temp[11][5] = new Wrestler("Andrew", 195, true, 12, 6);
      temp[11][6] = new Wrestler("Shane", 195, true, 12, 7);
      temp[11][7] = new Wrestler("Ben", 195, true, 12, 8);

      temp[12][0] = new Wrestler("Michael", 220, true, 12, 1);
      temp[12][1] = new Wrestler("David", 220, true, 12, 2);
      temp[12][2] = new Wrestler("Gordan", 220, true, 12, 3);
      temp[12][3] = new Wrestler("Daniel", 220, true, 12, 4);
      temp[12][4] = new Wrestler("Jason", 220, true, 12, 5);
      temp[12][5] = new Wrestler("Andrew", 220, true, 12, 6);
      temp[12][6] = new Wrestler("Shane", 220, true, 12, 7);
      temp[12][7] = new Wrestler("Ben", 220, true, 12, 8);
      
      temp[13][0] = new Wrestler("Michael", 285, true, 12, 1);
      temp[13][1] = new Wrestler("David", 285, true, 12, 2);
      temp[13][2] = new Wrestler("Gordan", 285, true, 12, 3);
      temp[13][3] = new Wrestler("Daniel", 285, true, 12, 4);
      temp[13][4] = new Wrestler("Jason", 285, true, 12, 5);
      temp[13][5] = new Wrestler("Andrew", 285, true, 12, 6);
      temp[13][6] = new Wrestler("Shane", 285, true, 12, 7);
      temp[13][7] = new Wrestler("Ben", 285, true, 12, 8);
      */
   }
}