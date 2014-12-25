import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatchWindow extends JPanel implements Runnable{
   
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT =  580;
   private final Color color1 = new Color(56, 196, 28);;
   private final Color color2 = Color.RED;
   private final PaperScore paperDisplay;
   
   private static int minutes, lastMinutes;
   private static int seconds, lastSeconds;
   
   private JLabel wrestlerScore1;
   private JLabel wrestlerScore2;
   private JLabel time;
   private int score1;
   private int score2;
   private int periodNum = 1;
   final JPanel thisPanel;
   
   private static boolean running = true;
   private static boolean counting = false;
   
   public MatchWindow(){
      thisPanel = this;
      minutes = lastMinutes = 2;
      seconds = lastSeconds = 0;
      score1 = 0;
      score2 = 0;
      setPreferredSize(new Dimension(WINDOW_WIDTH + 200, WINDOW_HEIGHT + 100));
            
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{WINDOW_WIDTH / 16, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 16};
      gbl.rowHeights = new int[]{WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 32, WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 32, WINDOW_HEIGHT / 25, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 25, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 25, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10};
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
      
      if(seconds < 10){
         time = new JLabel(minutes + ":0" + seconds);
      }else{
         time = new JLabel(minutes + ":" + seconds);
      }
      time.setHorizontalAlignment(JLabel.CENTER);
      time.setFont(new Font("Times New Roman", Font.PLAIN, 60));
      gbc.gridx = 3;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      gbc.fill = GridBagConstraints.BOTH;
      add(time, gbc);
      
      JPanel timeControl = new JPanel();
      gbc.gridy = 1;
      
      JButton play = new JButton("||>");
      //play.setIcon();
      play.setFocusPainted(false);
      play.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            
            counting = !counting;
         }
      });
      
      JButton reset = new JButton("Reset");
      reset.setFocusPainted(false);
      reset.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            if(!counting){
               final JFrame resetOptions = new JFrame("Set");
               resetOptions.setLayout(new FlowLayout());
               resetOptions.setSize(new Dimension(200, 150));
               GridBagLayout gbl = new GridBagLayout();
               gbl.columnWidths = new int[]{100, 100};
               gbl.rowHeights = new int[]{50, 50, 50};
               resetOptions.setLayout(gbl);
               
               GridBagConstraints gbc = new GridBagConstraints();
               gbc.fill = GridBagConstraints.BOTH;
               gbc.gridx = 0;
               gbc.gridy = 0;
               JLabel minutesLabel = new JLabel("Minutes: ");
               minutesLabel.setHorizontalAlignment(JLabel.CENTER);
               resetOptions.add(minutesLabel, gbc);
               
               JLabel secondsLabel = new JLabel("Seconds: ");
               secondsLabel.setHorizontalAlignment(JLabel.CENTER);
               gbc.gridx = 1;
               resetOptions.add(secondsLabel, gbc);
               
               final JTextField minutesText = new JTextField(String.valueOf(lastMinutes));
               minutesText.setHorizontalAlignment(JTextField.CENTER);
               gbc.gridy = 1;
               gbc.gridx = 0;
               resetOptions.add(minutesText, gbc);
               
               final JTextField secondsText = new JTextField(String.valueOf(lastSeconds));
               secondsText.setHorizontalAlignment(JTextField.CENTER);
               gbc.gridx = 1;
               resetOptions.add(secondsText, gbc);
               
               JButton set = new JButton("Set");
               set.addActionListener(new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                     minutes = lastMinutes = Integer.parseInt(minutesText.getText());
                     seconds = lastSeconds = Integer.parseInt(secondsText.getText());
                     if(seconds < 10){
                        time.setText(minutes + ":0" + seconds);
                     }else{
                        time.setText(minutes + ":" + seconds);
                     }
                     resetOptions.dispose();
                  }
               });
               gbc.gridx = 0;
               gbc.gridy = 2;
               gbc.gridwidth = 2;
               resetOptions.add(set, gbc);
               
               resetOptions.setLocationRelativeTo(null);
               updateUI();
               resetOptions.setVisible(true);               
            }
         }
      });
      
      timeControl.add(play);
      timeControl.add(reset);
      add(timeControl, gbc);
      
      final JLabel period = new JLabel(" Period " + periodNum);
      period.setFont(new Font("Times New Roman", Font.PLAIN, 20));
      period.setHorizontalAlignment(JLabel.CENTER);
      gbc.gridy = 2;
      add(period, gbc);
      
      JPanel periodChanger = new JPanel();
      gbc.gridx = 3;
      gbc.gridy = 3;

      JButton periodMinus = new JButton("-");
      periodMinus.setFocusPainted(false);
      periodMinus.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            if(periodNum > 1){
               periodNum--;
               if(periodNum < 4){
                  period.setText(" Period " + periodNum);
               }else if(periodNum == 4){
                  period.setText("Overtime");
               }else if(periodNum == 5){
                  period.setText("Tiebreaker 1");
               }else if(periodNum == 6){
                  period.setText("Tiebreaker 2");
               }else if(periodNum == 7){
                  period.setText("Ultimate Tiebreaker");
               }
            }
         }
      });
      periodChanger.add(periodMinus);

      JButton periodPlus = new JButton("+");
      periodPlus.setFocusPainted(false);
      periodPlus.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            if(periodNum < 7){
               periodNum++;
               if(periodNum < 4){
                  period.setText(" Period " + periodNum);
               }else if(periodNum == 4){
                  period.setText("Overtime");
               }else if(periodNum == 5){
                  period.setText("Tiebreaker 1");
               }else if(periodNum == 6){
                  period.setText("Tiebreaker 2");
               }else if(periodNum == 7){
                  period.setText("Ultimate Tiebreaker");
               }
            }
         }
      });
      periodChanger.add(periodPlus);
      
      add(periodChanger, gbc);
      
      paperDisplay = new PaperScore(WINDOW_WIDTH / 8 + 8, WINDOW_HEIGHT / 9, WINDOW_HEIGHT / 25);       
      JScrollPane scroll = new JScrollPane(paperDisplay);
      scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroll.setPreferredSize(new Dimension((WINDOW_WIDTH / 8 + 8) * 7, (WINDOW_HEIGHT / 9) * 3 + (WINDOW_HEIGHT / 25) * 3));
                        
      gbc.gridx = 1;
      gbc.gridy = 4;
      gbc.gridwidth = 7;
      gbc.gridheight = 6;
      add(scroll, gbc);                        

      
      int buttonStartX = 1;
      int buttonStartY = 10;     
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
            if(counting){
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
               Thread.sleep(1000);
            }
         }catch(InterruptedException e){
            e.printStackTrace();
         }
      }
   }
   
}