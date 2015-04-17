import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Bracket{
   private static final int[] weights = {106, 113, 120, 126, 132, 138, 145, 152, 160, 170, 182, 195, 220, 285};
   private Match[][] matches;
   private int bracketNum;
   private int numRounds, numWrestlers;
   private int currRound, currMatch;
   int width = 1000, height = 700;
   private JFrame championship, consolation;
   int heightOffset, heightOffset2;
   int h, w;
   private static final Font font = new Font("Monospaced", Font.PLAIN, 14);
   
   public Bracket(int bracketNum, int numWrestle) {
      this.bracketNum = bracketNum;
      this.numWrestlers = 8;
      this.heightOffset = 50;
      this.heightOffset2 = 30;
      //8  man heightoffset = 0
      //16 man heightoffset2 = 18
      //32 man heightoffset2 = 30
      currRound = 0;
      currMatch = 0;
      numRounds = (int) (Math.log(numWrestle) / Math.log(2)) * 2 - 2;
      matches = new Match[numRounds + 1][];
      int matchesPerRound = numWrestle;         
      w = width / numRounds;
      for(int i = 0; i < numRounds; i++)
      {
         if(i % 2 == 0)
         {
            matchesPerRound /= 2;            
         }
         matches[i] = new Match[matchesPerRound];
      }  
      matches[numRounds] = new Match[matchesPerRound];
      championship = new JFrame(weights[bracketNum] + "lb. Championship Bracket")
      {
         int h = height / numWrestlers;   
         public void paint(Graphics g){
            g.clearRect(0,0,width,height);
            g.setColor(Color.BLACK);
            g.setFont(font);
            for(int i = 0; i < matches.length; i++){ 
               for(int j = 0; j < matches[i].length; j++){
                  if(matches[i][j]  != null){
                     if(i == 0){
                           g.drawString(matches[i][j].wrestler1.name, i + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 2 * h + heightOffset);
                           g.drawString(matches[i][j].wrestler2.name, i + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 2 * h + heightOffset + h);
                           g.fillRect(i, j * 2 * h + heightOffset + 5, w, 2);
                           g.fillRect(i, j * 2 * h + heightOffset + h + 5, w, 2);
                           g.fillRect(i + w - 2, j * 2 * h + heightOffset + 5, 2, h);
                     }else if(i == matches.length - 1 && !matches[i][j].isConsolation){
                        int widthFactor = i / 2 + 1;
                        g.drawString(matches[i][j].wrestler1.name, widthFactor * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 4 * h + h + heightOffset2 + 10);
                        g.fillRect(widthFactor * w, j * 4 * h + h + heightOffset2+ 15, w, 2); 
                        //g.fillRect(widthFactor * w + 50, j * 4 * h + h + 15, 2, h);            
                     }else if(!matches[i][j].isConsolation){
                        int widthFactor = (i + 1) / 2;
                        g.drawString(matches[i][j].wrestler1.name, widthFactor * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 2 * h + h / 2 + heightOffset2 + 10);
                        g.drawString(matches[i][j].wrestler2.name, widthFactor * w + w / 2 - matches[i][j].wrestler2.name.length() * 4, j * 2 * h + h / 2 + h + heightOffset2 + 10);
                        g.fillRect(widthFactor * w, j * 2 * h + h / 2 + heightOffset2 + 15, w, 2);
                        g.fillRect(widthFactor * w, j * 2 * h + h / 2 + h + heightOffset2+ 15, w, 2);
                        g.fillRect(widthFactor * w + w - 2, j * 2 * h + h / 2 + heightOffset2 + 15, 2, h);
                     }                           
                  }
               }
               if(i % 2 == 0)
                  h *= 2;  
            }
            h = height / numWrestlers;
         }
      };
      consolation = new JFrame(weights[bracketNum] + "lb. Consolation Bracket")
      {
            int h = (int) (height / ((numWrestlers / 2 + Math.floor(numRounds / 2)) * 2));
            int matchesPerRound = numWrestlers / 4;
             
            public void paint(Graphics g){
            g.clearRect(0,0,width,height);
            g.setColor(Color.BLACK);
            g.setFont(font);
            for(int i = 0; i < matches.length; i++){ 
               int consolationIndex = 0;
               for(int j = 1; j < matches[i].length; j++){
                  if(matches[i][j] != null && matches[i][j].isConsolation){
                     g.drawString(matches[i][j].wrestler1.name, (i - 1) * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, height -  (i - 1) * (h / (int)Math.floor((i + 1) / 2.0))/2 - (matchesPerRound - consolationIndex) * 2 * h - h / 2 - 150);
                     g.drawString(matches[i][j].wrestler2.name, (i - 1) * w + w / 2 - matches[i][j].wrestler2.name.length() * 4, height -  (i - 1) * (h / (int)Math.floor((i + 1) / 2.0))/2 - (matchesPerRound - consolationIndex) * 2 * h + h / 2 - 150);
                     g.fillRect((i - 1) * w, height -  (i - 1) * (h / (int)Math.floor((i + 1) / 2.0))/2 - (matchesPerRound - consolationIndex) * 2 * h - h / 2 + 15 - 150, w, 2);
                     g.fillRect((i - 1) * w, height -  (i - 1) * (h / (int)Math.floor((i + 1) / 2.0))/2 - (matchesPerRound - consolationIndex) * 2 * h + h / 2 + 15 - 150, w, 2);
                     g.fillRect((i - 1) * w + w - 2, height - (i - 1) * (h / (int)Math.floor((i + 1) / 2.0))/2 - (matchesPerRound - consolationIndex) * 2 * h - h / 2 + 15 - 150, 2, h);                                             
                     consolationIndex++;
                  }
               }
               if(i % 2 == 0 && h != 0)
               {
                  h *= 2;  
                  matchesPerRound /= 2;
               }
            }
            h = (int) (height / ((numWrestlers / 2 + Math.floor(numRounds / 2)) * 2));
            matchesPerRound = numWrestlers / 4;
         }     
      };
      championship.setSize(new Dimension(width,height));  
      consolation.setSize(new Dimension(width,height));  
      championship.setLocationRelativeTo(null);
      consolation.setLocationRelativeTo(null);
      championship.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      consolation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
   }
   
   public void showChampionship()
   {
      championship.setVisible(true);
      championship.getGraphics().setFont(font);
   }
   
   public void showConsolation()
   {
      consolation.setVisible(true);
      consolation.getGraphics().setFont(font);
   }
   
   public void addMatch(Match m)
   {
      matches[currRound][currMatch] = m;
      currMatch++;
      if(currMatch > matches[currRound].length - 1){
         currRound++;
         currMatch = 0;
      }
   }   
}


/*
               if(!matches[i][j].isConsolation){
                  int startY = j * ( h + 10 ) + h / 2 + 40;
                  g.setColor(Color.BLACK);
                  g.drawString(matches[i][j].wrestler1.name, i * w + 50, startY);
                  g.drawString(matches[i][j].wrestler2.name, i * w + 50, startY + 40);                
               }else{
                  int startY = consolationIndex * ( h + 10 ) + h / 2 + 400;
                  g.setColor(Color.RED);
                  g.drawString(matches[i][j].wrestler1.name, i * w + 50, startY);
                  g.drawString(matches[i][j].wrestler2.name, i * w + 50, startY + 40);
                  consolationIndex++;
               }
*/