import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Bracket{
   private static final int[] weights = {106, 113, 120, 126, 132, 138, 145, 152, 160, 170, 182, 195, 220, 285};
   private static final Font WEIGHT_FONT = new Font("Monospaced", Font.BOLD, 32);
   private Match[][] matches;
   private int bracketNum;
   private int numRounds, numWrestlers;
   private int currRound, currMatch;
   int width = 1000, height = 700;
   private JFrame championship, consolation;
   int heightOffset, heightOffset2;
   private static final Font font = new Font("Monospaced", Font.PLAIN, 14);
   
   public Bracket(int bracketNum, int numWrestle) {
      this.bracketNum = bracketNum;
      this.numWrestlers = numWrestle;
      if(numWrestle == 8)
      {
         this.heightOffset = 60;
         this.heightOffset2 = 10;
      }
      else if(numWrestle == 16)
      {
         this.heightOffset = 60;
         this.heightOffset2 = 30;
      }
      else
      {
         this.heightOffset = 60;
         this.heightOffset2 = 40;
      }
      //8  man heightoffset = 0
      //16 man heightoffset2 = 18
      //32 man heightoffset2 = 30
      currRound = 0;
      currMatch = 0;
      numRounds = (int) (Math.log(numWrestle) / Math.log(2)) * 2 - 2;
      matches = new Match[numRounds + 1][];
      int matchesPerRound = numWrestle;               
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
         public void paint(Graphics g){             
            int w = width / (numRounds / 2 + 2);
            int h = height / numWrestlers - 2; 
            g.clearRect(0,0,width,height);
            g.setColor(Color.BLACK);
            g.setFont(WEIGHT_FONT);
            g.drawString(weights[bracketNum] + " lbs.", width - 200, 100);
            g.setFont(font);
            for(int i = 0; i < matches.length; i++){ 
               for(int j = 0; j < matches[i].length; j++){
                  if(matches[i][j]  != null){
                     if(i == 0){
                           g.drawString(matches[i][j].wrestler1.name, i + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 2 * h + heightOffset);
                           g.drawString(matches[i][j].wrestler2.name, i + w / 2 - matches[i][j].wrestler2.name.length() * 4, j * 2 * h + heightOffset + h);
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
         }
      };
      consolation = new JFrame(weights[bracketNum] + "lb. Consolation Bracket")
      {            
             
         public void paint(Graphics g)
         {               
            int w = width / numRounds;
            int h = (int) height / (numWrestlers / 2) - 10;
            int matchesPerRound = numWrestlers / 4;
            int yOffset = 0;
            int yOffConst = 0;
            g.clearRect(0,0,width,height);
            g.setColor(Color.BLACK);
            g.setFont(WEIGHT_FONT);
            g.drawString(weights[bracketNum] + " lbs.", width - 200, 100);  
            g.setFont(font);
            for(int i = 1; i < matches.length; i++)
            { 
               int consolationIndex = 0;
               for(int j = 0; j < matches[i].length; j++){
                  if(matches[i][j] != null && matches[i][j].isConsolation && i != matches.length - 1){
                     int y = (matchesPerRound - 1 - consolationIndex) * 2 * h + yOffset;
                     g.drawString(matches[i][j].wrestler1.name, (i - 1) * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, height -  y - h - 30);
                     g.drawString(matches[i][j].wrestler2.name, (i - 1) * w + w / 2 - matches[i][j].wrestler2.name.length() * 4, height -  y - 30);
                     g.fillRect((i - 1) * w, height - y - h - 25, w, 2);
                     g.fillRect((i - 1) * w, height - y - 25, w, 2);
                     g.fillRect((i - 1) * w + w - 2, height - y - h - 25, 2, h);
                     consolationIndex++;
                  }
                  else if(matches[i][j] != null && matches[i][j].isConsolation)
                  {
                     int y = (matchesPerRound - 1 - consolationIndex) * 2 * h + yOffset;                     
                     g.drawString(matches[i][j].wrestler1.name, (i - 1) * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, height -  y - 30);
                     g.fillRect((i - 1) * w, height - y - 25, w, 2);
                     consolationIndex++;
                  }
               }                                                           
               if(i % 2 == 0)
               {
                  h *= 2;  
                  matchesPerRound /= 2;
                  
               }
               else
               {
                  yOffConst *= 2;
               }
               
               if(yOffset == 0)
               {
                  yOffConst = h / 2;
               } 
               yOffset += yOffConst;               
            }
         }     
      };
      championship.setSize(new Dimension(width,height));  
      consolation.setSize(new Dimension(width,height));  
      championship.setLocationRelativeTo(null);
      consolation.setLocationRelativeTo(null);
      championship.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      consolation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
   }
   
   public void showChampionship()
   {
      championship.setVisible(true);
   }
   
   public void showConsolation()
   {
      consolation.setVisible(true);
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