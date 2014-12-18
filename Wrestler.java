public class Wrestler {
   public final String name;
   private final int weightClass;
   private int tournamentScore;   
   public final boolean isScorer;
   private final int grade;
   private final int seed;
   public int byePoints;
   
   // TO DO: INCLUDE POINTS FOR A BYE
   public Wrestler(String name, int weightClass, boolean isScorer, int grade, int seed) {
      this.name = name;
      this.weightClass = weightClass;
      tournamentScore = 0;
      this.isScorer = isScorer;
      this.grade = grade;
      this.seed = seed;
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