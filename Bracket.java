import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class Bracket extends JFrame{

   private Match[][] matches;
   private int bracketNum;
   private int numRounds;
   private int currRound, currMatch;
   int width = 1000, height = 800;
   
   public Bracket(int bracketNum, int numWrestlers) {
      this.bracketNum = bracketNum;
      currRound = 0;
      currMatch = 0;
      numRounds = (int) (Math.log(numWrestlers) / Math.log(2)) * 2 - 2;
      matches = new Match[numRounds + 1][];
      int matchesPerRound = numWrestlers;   
      for(int i = 0; i < numRounds; i++)
      {
         if(i % 2 == 0)
         {
            matchesPerRound /= 2;            
         }
         matches[i] = new Match[matchesPerRound];
      }  
      matches[numRounds] = new Match[matchesPerRound];
      setSize(new Dimension(width,height));  
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
   }
   
   public void showBracket()
   {
      setVisible(true);
   }
   
   public void addMatch(Match m)
   {
      if(bracketNum == 0)
      {
         System.out.println();
      }
      matches[currRound][currMatch] = m;
      currMatch++;
      if(currMatch > matches[currRound].length - 1){
         currRound++;
         currMatch = 0;
      }
      repaint();
   }
   
   public void paint(Graphics g)
   {
      g.clearRect(0,0,width,height);
      g.setColor(Color.BLACK);
      int w = width / matches.length;
      for(int i = 0; i < matches.length - 1; i++)
      {
         int h;
         if(i % 2 == 0){
            h = (height - matches[i].length * 10) / (2 * (matches[i].length));
         }
         else{
            h = (height - matches[i].length * 10) / (2 * (matches[i].length / 2 ));
         }
         int consolationIndex = 0;
         for(int j = 0; j < matches[i].length; j++)
         {
            if(matches[i][j]  != null){
               if(!matches[i][j].isConsolation){
                  g.setColor(Color.BLACK);
                  g.drawString(matches[i][j].wrestler1.name, i * w + 50, j * (h + 10) + h / 2 + 40);
                  g.drawString(matches[i][j].wrestler2.name, i * w + 50, j * (h + 10) + h / 2 + 60);
               }else{
                  g.setColor(Color.RED);
                  g.drawString(matches[i][j].wrestler1.name, i * w + 50, consolationIndex * (h + 10) + 390 + h / 2);
                  g.drawString(matches[i][j].wrestler2.name, i * w + 50, consolationIndex * (h + 10) + 420 + h / 2);
                  consolationIndex++;
               }                  
            }
         }
      }
   } 
}