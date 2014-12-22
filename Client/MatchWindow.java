import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatchWindow extends JPanel implements Runnable{
   
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT =  580;
   private final Color color1 = new Color(56, 196, 28);;
   private final Color color2 = Color.RED;
   private final PaperScore paperDisplay;
   
   private static int minutes;
   private static int seconds;
   
   private JLabel wrestlerScore1;
   private JLabel wrestlerScore2;
   private JLabel time;
   private int score1;
   private int score2;
   private int periodNum = 2;
   
   private static boolean running = true;
   
   public MatchWindow(){
      minutes = 2;
      seconds = 0;
      score1 = 0;
      score2 = 0;
      setPreferredSize(new Dimension(WINDOW_WIDTH + 200, WINDOW_HEIGHT + 100));
            
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{WINDOW_WIDTH / 16, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 16};
      gbl.rowHeights = new int[]{WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 25, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 25, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 25, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10};
      setLayout(gbl);
      
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.BOTH;

      wrestlerScore1 = new JLabel(String.valueOf(score1));
      wrestlerScore1.setForeground(color1);
      wrestlerScore1.setFont(new Font("Times New Roman", Font.PLAIN, 60));
      wrestlerScore1.setHorizontalAlignment(JLabel.CENTER);
      add(wrestlerScore1, gbc);
      
      gbc.gridx = 5;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.BOTH;

      wrestlerScore2 = new JLabel(String.valueOf(score2));
      wrestlerScore2.setForeground(color2);
      wrestlerScore2.setFont(new Font("Times New Roman", Font.PLAIN, 60));
      wrestlerScore2.setHorizontalAlignment(JLabel.CENTER);
      add(wrestlerScore2, gbc);
      
      time = new JLabel(minutes + ":" + seconds);
      time.setHorizontalAlignment(JLabel.CENTER);
      time.setFont(new Font("Times New Roman", Font.PLAIN, 60));
      gbc.gridx = 3;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.BOTH;
      add(time, gbc);
      
      JLabel period = new JLabel("Period " + periodNum);
      period.setHorizontalAlignment(JLabel.CENTER);
      gbc.gridx = 3;
      gbc.gridy = 1;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.BOTH;
      add(period, gbc);
      
      paperDisplay = new PaperScore(WINDOW_WIDTH / 8 + 8, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 25);       
      JScrollPane scroll = new JScrollPane(paperDisplay);
      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroll.setPreferredSize(new Dimension((WINDOW_WIDTH / 8 + 8) * 7, (WINDOW_HEIGHT / 9) * 3 + (WINDOW_HEIGHT / 25) * 3));
                        
      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.gridwidth = 7;
      gbc.gridheight = 6;
      add(scroll, gbc);                        

      
      int buttonStartX = 1;
      int buttonStartY = 8;     
      createButton("Takedown", "T2", buttonStartX, buttonStartY, 1, color1);      
      createButton("Escape", "E1", buttonStartX + 1, buttonStartY, 1, color1);      
      createButton("Reversal", "R2", buttonStartX + 2, buttonStartY, 1, color1);      
      createButton("2 Pt. Nearfall", "N2", buttonStartX, buttonStartY + 1, 1, color1);      
      createButton("3 Pt. Nearfall", "N3", buttonStartX + 1, buttonStartY + 1, 1, color1);
      createButton("Tech. Violation", "TV1", buttonStartX + 2, buttonStartY + 1, 1, color1);
      createButton("Stalling", buttonStartX, buttonStartY + 2, 1, color1);
      createButton("Blood Time", buttonStartX + 1, buttonStartY + 2, 1, color1);
      createButton("Pin", buttonStartX + 2, buttonStartY + 2, 1, color1);
      
      int buttonStartX_2 = 5;
      createButton("Takedown", "T2", buttonStartX_2, buttonStartY, 1, color2);      
      createButton("Escape", "E1", buttonStartX_2 + 1, buttonStartY, 1, color2);      
      createButton("Reversal", "R2", buttonStartX_2 + 2, buttonStartY, 1, color2);      
      createButton("2 Pt. Nearfall", "N2", buttonStartX_2, buttonStartY + 1, 1, color2);      
      createButton("3 Pt. Nearfall", "N3", buttonStartX_2 + 1, buttonStartY + 1, 1, color2);
      createButton("Tech. Violation", "TV1", buttonStartX_2 + 2, buttonStartY + 1, 1, color2);
      createButton("Stalling", buttonStartX_2, buttonStartY + 2, 1, color2);
      createButton("Blood Time", buttonStartX_2 + 1, buttonStartY + 2, 1, color2);
      createButton("Pin",buttonStartX_2 + 2, buttonStartY + 2, 1, color2); 
      
      new Thread(this).start();     
  }
         
   private void createButton(final String text, int gridx, int gridy, int gridwidth, Color color){
      JButton button = new JButton(text);
      button.setFocusPainted(false);
      button.setForeground(color);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.fill = GridBagConstraints.BOTH;
      
      add(button, gbc);
      updateUI();
   }
   
   private void createButton(final String text, final String scoreDetails, final int gridx, final int gridy, int gridwidth, final Color color){
      JButton button = new JButton(text);
      button.setFocusPainted(false);
      button.setForeground(color);
      final JPanel temp = this;
      button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            JButton scoreButton = new JButton(scoreDetails);
            scoreButton.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                  System.out.println(scoreDetails + " was pressed");
               }
            });
            paperDisplay.addScoreButton(scoreButton, periodNum, gridx / 5 + 1);
            if(gridx / 5 == 0){
               score1 += Character.getNumericValue(scoreDetails.charAt(scoreDetails.length() - 1));
               wrestlerScore1.setText(String.valueOf(score1));
            }else{
               score2 += Character.getNumericValue(scoreDetails.charAt(scoreDetails.length() - 1));
               wrestlerScore2.setText(String.valueOf(score2));
            }
            temp.updateUI();
         }
      });
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.fill = GridBagConstraints.BOTH;
      
      add(button, gbc);
      
      updateUI();
   }
   
   //creates a new button to be shown on wrestler 1's score paper
   //creates a new button to be shown on wrestler 2's score paper
   private void createScoreButton(final String details, final JPanel panel){
      JButton temp = new JButton(details);
      temp.setFocusPainted(false);
      temp.setPreferredSize(new Dimension(50,25));
      temp.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e){
            System.out.println(details + " pressed");
         }
      });
      panel.add(temp);
      panel.updateUI();
   }
   
   public void run(){
      while(running){
         try{
            seconds -= 1;
            if(seconds < 0){
               minutes -= 1;
               seconds = 59;
               if(minutes < 0){
                  running = false;
                  minutes = 0;
                  seconds = 0;
               }
            }
            if(seconds < 10)
               time.setText(minutes + ":" + "0" + seconds);
            else
               time.setText(minutes + ":" + seconds);
            updateUI();
            Thread.sleep(1000);
         }catch(InterruptedException e){
            e.printStackTrace();
         }
      }
   }
   
}