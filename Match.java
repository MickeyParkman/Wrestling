public class Match{
   
   private int matchNumber;
   
   private String wrestler1, wrestler2;
   private int winner;
   
   //stores the information on how the wrestler won. ie pin 1:23
   String winStatement;
   
   public Match(int matchNumber){
      this.matchNumber = matchNumber;
   }
   
   public void setWinner(int winner){
      this.winner = winner;
   }
   
   public String getWinner(){
      if(winner == 1)
         return wrestler1;
      else if(winner == 2)
         return wrestler2;
      else   
         return "";
   }
   
}