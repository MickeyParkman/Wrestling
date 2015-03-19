import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Bracket extends JFrame{

   private Match[][] matches;
   private int bracketNum;
   private int numRounds;
   private int currRound, currMatch;
   int width = 1000, height = 800;
   int heightOffset = 60;
   int h, w;
   private static final Font font = new Font("Monospaced", Font.PLAIN, 14);
   
   public Bracket(int bracketNum, int numWrestlers) {
      this.bracketNum = bracketNum;
      currRound = 0;
      currMatch = 0;
      numRounds = (int) (Math.log(numWrestlers) / Math.log(2)) * 2 - 2;
      matches = new Match[numRounds + 1][];
      int matchesPerRound = numWrestlers;   
      h = height / 8;
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
      setSize(new Dimension(width,height));  
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
   }
   
   public void showBracket()
   {
      setVisible(true);
      getGraphics().setFont(font);
   }
   
   public void addMatch(Match m)
   {
      matches[currRound][currMatch] = m;
      currMatch++;
      if(currMatch > matches[currRound].length - 1){
         currRound++;
         currMatch = 0;
      }
      repaint();
   }
   
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
                  g.drawString(matches[i][j].wrestler1.name, widthFactor * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 4 * h + h + 10);
                  g.fillRect(widthFactor * w, j * 4 * h + h + 15, w, 2); 
                  //g.fillRect(widthFactor * w + 50, j * 4 * h + h + 15, 2, h);            
               }else if(!matches[i][j].isConsolation){
                  int widthFactor = (i + 1) / 2;
                  g.drawString(matches[i][j].wrestler1.name, widthFactor * w + w / 2 - matches[i][j].wrestler1.name.length() * 4, j * 2 * h + h / 2 + 10);
                  g.drawString(matches[i][j].wrestler2.name, widthFactor * w + w / 2 - matches[i][j].wrestler2.name.length() * 4, j * 2 * h + h / 2 + h + 10);
                  g.fillRect(widthFactor * w, j * 2 * h + h / 2 + 15, w, 2);
                  g.fillRect(widthFactor * w, j * 2 * h + h / 2 + h + 15, w, 2);
                  g.fillRect(widthFactor * w + w - 2, j * 2 * h + h / 2 + 15, 2, h);
               }                           
            }
         }
         if(i % 2 == 0)
            h *= 2;  
      }
      h = height / 8;
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