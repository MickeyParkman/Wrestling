public class Start{
   public static void main(String[] args){
      Wrestler[][] wrestlers = new Wrestler[14][8];
      Parser parse = new Parser(wrestlers);
      SeedingMeeting seeds = new SeedingMeeting(wrestlers);
   }
}