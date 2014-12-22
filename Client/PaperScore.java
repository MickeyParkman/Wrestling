import java.awt.*;
import javax.swing.*;

public class PaperScore extends JPanel{

   private final JPanel period1;
   private final JPanel period2;
   private final JPanel period3;
   private final JPanel overtime;
   private final JPanel tiebreaker1;
   private final JPanel tiebreaker2;
   private final JPanel ultimateTiebreaker;
   private final JPanel period1_2;
   private final JPanel period2_2;
   private final JPanel period3_2;
   private final JPanel overtime_2;
   private final JPanel tiebreaker1_2;
   private final JPanel tiebreaker2_2;
   private final JPanel ultimateTiebreaker_2;
   
   private final int WIDTH, SCORE_HEIGHT, LABEL_HEIGHT;

   public PaperScore(int width, int scoreHeight, int labelHeight){
      this.WIDTH = width;
      this.SCORE_HEIGHT = scoreHeight;
      this.LABEL_HEIGHT = labelHeight;
      setPreferredSize(new Dimension(width * 7, scoreHeight * 7 + labelHeight * 7));
      
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{width, width, width, width, width, width, width};
      gbl.rowHeights = new int[]{labelHeight, scoreHeight, 
                                 labelHeight, scoreHeight, 
                                 labelHeight, scoreHeight,
                                 labelHeight, scoreHeight, 
                                 labelHeight, scoreHeight, 
                                 labelHeight, scoreHeight, 
                                 labelHeight, scoreHeight,};
      
      setLayout(gbl);
      
      period1 = new JPanel();
      period2 = new JPanel();
      period3 = new JPanel();
      overtime = new JPanel();
      tiebreaker1 = new JPanel();
      tiebreaker2 = new JPanel();
      ultimateTiebreaker = new JPanel();
      period1_2 = new JPanel();
      period2_2 = new JPanel();
      period3_2 = new JPanel();
      overtime_2 = new JPanel();
      tiebreaker1_2 = new JPanel();
      tiebreaker2_2 = new JPanel();
      ultimateTiebreaker_2 = new JPanel();      
      
      int scoreBoardX = 0;
      int scoreBoardY = 0;
      createScoreboard(period1,              "Period 1",             scoreBoardX, scoreBoardY     , 3);      
      createScoreboard(period2,              "Period 2",             scoreBoardX, scoreBoardY + 2 , 3);           
      createScoreboard(period3,              "Period 3",             scoreBoardX, scoreBoardY + 4 , 3);
      createScoreboard(overtime,             "Overtime",             scoreBoardX, scoreBoardY + 6 , 3);      
      createScoreboard(tiebreaker1,          "Tiebreaker 1",         scoreBoardX, scoreBoardY + 8 , 3);           
      createScoreboard(tiebreaker2,          "Tiebreaker 2",         scoreBoardX, scoreBoardY + 10, 3);
      createScoreboard(ultimateTiebreaker,   "Ultimate Tiebreaker",  scoreBoardX, scoreBoardY + 12, 3);
      scoreBoardX = 4;             
      createScoreboard(period1_2,            "Period 1",             scoreBoardX, scoreBoardY     , 3);            
      createScoreboard(period2_2,            "Period 2",             scoreBoardX, scoreBoardY + 2 , 3);            
      createScoreboard(period3_2,            "Period 3",             scoreBoardX, scoreBoardY + 4 , 3);
      createScoreboard(overtime_2,             "Overtime",             scoreBoardX, scoreBoardY + 6 , 3);      
      createScoreboard(tiebreaker1_2,          "Tiebreaker 1",         scoreBoardX, scoreBoardY + 8 , 3);           
      createScoreboard(tiebreaker2_2,          "Tiebreaker 2",         scoreBoardX, scoreBoardY + 10, 3);
      createScoreboard(ultimateTiebreaker_2,   "Ultimate Tiebreaker",  scoreBoardX, scoreBoardY + 12, 3);
   }
   
   private void createScoreboard(JPanel pane, String text, int gridx, int gridy, int gridwidth){
      JLabel label = new JLabel(text);
      label.setHorizontalAlignment(JLabel.CENTER);
      GridBagConstraints gbc_label = new GridBagConstraints();
      gbc_label.gridx = gridx;
      gbc_label.gridy = gridy;
      gbc_label.gridwidth = gridwidth;
      gbc_label.fill = GridBagConstraints.BOTH;
      pane.setPreferredSize(new Dimension(this.WIDTH * 3, this.SCORE_HEIGHT));
      pane.setBackground(Color.BLACK);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = gridx;
      gbc.gridy = gridy + 1;
      gbc.gridwidth = gridwidth;
      
      add(label, gbc_label);
      add(pane, gbc);
      updateUI();
   }
   
   public void addScoreButton(JButton button, int round, int wrestler){
      if(wrestler == 1){
         if(round == 1){
            period1.add(button);
         }else if(round == 2){
            period2.add(button);
         }else if(round == 3){
            period3.add(button);
         }else if(round == 4){
            overtime.add(button);
         }else if(round == 5){
            tiebreaker1.add(button);
         }else if(round == 6){
            tiebreaker2.add(button);
         }else if(round == 7){
            ultimateTiebreaker.add(button);
         }
      }else if(wrestler == 2){
         if(round == 1){
            period1_2.add(button);
         }else if(round == 2){
            period2_2.add(button);
         }else if(round == 3){
            period3_2.add(button);
         }else if(round == 4){
            overtime_2.add(button);
         }else if(round == 5){
            tiebreaker1_2.add(button);
         }else if(round == 6){
            tiebreaker2_2.add(button);
         }else if(round == 7){
            ultimateTiebreaker_2.add(button);
         }
      }
      updateUI();
   }
}