public class Match{
   
   private int matchNumber;
   
   private Wrestler wrestler1, wrestler2;
   private int winner;
   
   private int weightClass;
   
   //stores the information on how the wrestler won. ie pin 1:23
   private String winStatement;
   
   public Match(int matchNumber){
      this.winner = -1;
      this.matchNumber = matchNumber;
   }
   
   public void setWinner(int winner){
      this.winner = winner;
   }
   
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
   
}