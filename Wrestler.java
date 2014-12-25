public class Wrestler {
   public final String name;
   public final String teamName;
   private int tournamentScore;
   public final boolean isScorer;
   private final int grade;
   public int byePoints;
   
   // TO DO: INCLUDE POINTS FOR A BYE
   public Wrestler(String name, String teamName, boolean isScorer, int grade) {
      this.name = name;
      this.teamName = teamName;
      this.weightClass = weightClass;
      tournamentScore = 0;
      this.isScorer = isScorer;
      this.grade = grade;
      byePoints = 0;
   }
   
   public int getWeight() {
      return weightClass;
   }
   
   public int getTournamentScore() {
      return tournamentScore;
   }
   
   public void addTournamentScore(int points) {
      tournamentScore += points;
   }
   
   public void subTournamentScore(int points) {
      tournamentScore -= points;
   }
   
   public int getGrade() {
      return grade;
   }
   
   public int getSeed() {
      return seed;
   }
}