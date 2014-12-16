
public class Tournament{

   public Bracket[] brackets;
   
   
   
   public Tournament(){
      for(int t: Bracket.test)
         System.out.println(t);
      brackets = new Bracket[13];
      for(int i = 0; i < brackets.length; i++){
         brackets[i] = new Bracket(i);
      }
      for(int t: Bracket.test)
         System.out.println(t);
   }
   
   public static void main(String[] args){
      new Tournament();
   }

}