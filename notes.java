// Things we need
// Something to track wins by By. If someone has a bye,
   public static final int[] startIndexProportions = new int{0,7,14,17.5,21,22.75, 24.5};
   int roundNum;
   public Match(int matchNumber){
      this.winner = -1;
      this.matchNumber = matchNumber;
      for(int i = 1; i < startIndexProportions.length; i++){
         if(matchNumber < startIndexProportions[i] * Tournament.wrestlersPerBracket){
            roundNum = i - 1;
            break;  
         }
      }
   }
