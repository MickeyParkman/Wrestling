//import java.util.scanner;

public class Team {
   public int arraySize;
   public String[] teams;
   public int[] scores;
   
   public Team() {
      arraySize = 0;
      teams = new String[10];
      scores = new int[10];
   }
   
   public Team(int numTeams){
      arraySize = 0;
      teams = new String[numTeams];
      scores = new int[numTeams];
   }
   
   // updates the score of the corresponding team, if the team is not
   // in the array, creates a new slot for the team and sets initial score
   public void updateScore(String team, int score) {
      int index = teamIndex(team);
      if (index == -1) {
    		if(teams.length == arraySize) { // resize
   			String[] new_teams = new String[teams.length*2];
            int[] new_scores = new int[scores.length*2];
   			for(int i=0; i < arraySize; i++) {
   				new_teams[i] = teams[i];
               new_scores[i] = scores[i];
   			}
            teams = new_teams;
            scores = new_scores;
   		}
         teams[arraySize] = team;
         scores[arraySize] = score;
         arraySize++;
      } else {
         scores[index] += score;
      }
   }
   
   // finds the index of the team
   private int teamIndex(String team) {
      for (int i = 0; i < arraySize; i++) {
         if (teams[i].equals(team)) {
            return i;
         }
      }
      return -1;
   }
   
   public void printScores() {
      for (int i = 0; i < arraySize; i++) {
         System.out.println(teams[i] + " : " + scores[i]);
      }
   }
   // david is a sexy stud
}
