//import java.util.scanner;

public class Team {
   public String teamName;
   // assign the Wrestler.name as the hashing string
   private HashTable<String, Wrestler> wrestlers;
   private int teamScore;
   
   public Team(String teamName) {
      this.teamName = teamName;
      teamScore = 0;
   }
   
   // hashes a wrestler into the table using the wrestler's name as a key
   public void addWrestler(Wrestler wr) {
      put(wr.name, wr);
   }
   
   public void setScore(int score) {
      teamScore = score;
   }
}