public class Start{
   public static void main(String[] args){
      int numWrestlers = 16;
      Wrestler[][] wrestlers = new Wrestler[14][numWrestlers];
      Parser parse = new Parser(wrestlers);
      SeedingMeeting seeds = new SeedingMeeting(wrestlers, numWrestlers);
   }
}

//change stuff in here, parser, and tournament. Pass in num wrestlers?