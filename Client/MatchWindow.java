import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatchWindow extends JPanel{
   
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT =  580;
   private int roundNum = 2;
   
   public MatchWindow(){
      setPreferredSize(new Dimension(WINDOW_WIDTH + 200, WINDOW_HEIGHT + 100));
            
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{WINDOW_WIDTH / 16, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8};
      gbl.rowHeights = new int[]{WINDOW_HEIGHT / 4, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10};
      setLayout(gbl);
            
      final JPanel round1 = new JPanel();
      round1.setPreferredSize(new Dimension(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8));
      round1.setBackground(Color.BLACK);
      GridBagConstraints gbc_round1 = new GridBagConstraints();
      gbc_round1.gridx = 1;
      gbc_round1.gridy = 2;
      gbc_round1.gridwidth = 3;
      gbc_round1.fill = GridBagConstraints.BOTH;
      
      final JPanel round2 = new JPanel();
      round2.setPreferredSize(new Dimension(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8));
      round2.setBackground(Color.BLACK);
      GridBagConstraints gbc_round2 = new GridBagConstraints();
      gbc_round2.gridx = 1;
      gbc_round2.gridy = 3;
      gbc_round2.gridwidth = 3;
      gbc_round2.fill = GridBagConstraints.BOTH;
      
      final JPanel round3 = new JPanel();
      round3.setPreferredSize(new Dimension(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8));
      round3.setBackground(Color.BLACK);
      GridBagConstraints gbc_round3 = new GridBagConstraints();
      gbc_round3.gridx = 1;
      gbc_round3.gridy = 4;
      gbc_round3.gridwidth = 3;
      gbc_round3.fill = GridBagConstraints.BOTH;
      
      final JPanel round1_2 = new JPanel();
      round1_2.setPreferredSize(new Dimension(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8));
      round1_2.setBackground(Color.BLACK);
      GridBagConstraints gbc_round1_2 = new GridBagConstraints();
      gbc_round1_2.gridx = 5;
      gbc_round1_2.gridy = 2;
      gbc_round1_2.gridwidth = 3;
      gbc_round1_2.fill = GridBagConstraints.BOTH;
      
      final JPanel round2_2 = new JPanel();
      round2_2.setPreferredSize(new Dimension(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8));
      round2_2.setBackground(Color.BLACK);
      GridBagConstraints gbc_round2_2 = new GridBagConstraints();
      gbc_round2_2.gridx = 5;
      gbc_round2_2.gridy = 3;
      gbc_round2_2.gridwidth = 3;
      gbc_round2_2.fill = GridBagConstraints.BOTH;
      
      final JPanel round3_2 = new JPanel();
      round3_2.setPreferredSize(new Dimension(WINDOW_WIDTH / 8, WINDOW_HEIGHT / 8));
      round3_2.setBackground(Color.BLACK);
      GridBagConstraints gbc_round3_2 = new GridBagConstraints();
      gbc_round3_2.gridx = 5;
      gbc_round3_2.gridy = 4;
      gbc_round3_2.gridwidth = 3;
      gbc_round3_2.fill = GridBagConstraints.BOTH;
      
      JButton takedown = new JButton("Takedown");
      takedown.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e){
            if(roundNum == 1){
               createScoreButton("T2", round1);
            }else if(roundNum == 2){
               createScoreButton("T2", round2);
            }else if(roundNum == 3){
               createScoreButton("T2", round3);
            }
         }
      });
      
      int buttonStartX = 1;
      int buttonStartY = 5;
      Color color1 = Color.GREEN;
      createButton("Takedown", buttonStartX, buttonStartY, 1, color1);      
      createButton("Escape", buttonStartX + 1, buttonStartY, 1, color1);      
      createButton("Reversal", buttonStartX + 2, buttonStartY, 1, color1);      
      createButton("2 Pt. Nearfall", buttonStartX, buttonStartY + 1, 1, color1);      
      createButton("3 Pt. Nearfall", buttonStartX + 1, buttonStartY + 1, 1, color1);
      createButton("Tech. Violation", buttonStartX + 2, buttonStartY + 1, 1, color1);
      createButton("Stalling", buttonStartX, buttonStartY + 2, 1, color1);
      createButton("Blood Time", buttonStartX + 1, buttonStartY + 2, 1, color1);
      createButton("Pin", buttonStartX + 2, buttonStartY + 2, 1, color1);
      
      int buttonStartX_2 = buttonStartY;
      Color color2 = Color.RED;
      createButton("Takedown", buttonStartX_2, buttonStartY, 1, color2);      
      createButton("Escape", buttonStartX_2 + 1, buttonStartY, 1, color2);      
      createButton("Reversal", buttonStartX_2 + 2, buttonStartY, 1, color2);      
      createButton("2 Pt. Nearfall", buttonStartX_2, buttonStartY + 1, 1, color2);      
      createButton("3 Pt. Nearfall", buttonStartX_2 + 1, buttonStartY + 1, 1, color2);
      createButton("Tech. Violation", buttonStartX_2 + 2, buttonStartY + 1, 1, color2);
      createButton("Stalling", buttonStartX_2, buttonStartY + 2, 1, color2);
      createButton("Blood Time", buttonStartX_2 + 1, buttonStartY + 2, 1, color2);
      createButton("Pin",buttonStartX_2 + 2, buttonStartY + 2, 1, color2);
            
      add(round1, gbc_round1);
      add(round2, gbc_round2);
      add(round3, gbc_round3);
      add(round1_2, gbc_round1_2);
      add(round2_2, gbc_round2_2);
      add(round3_2, gbc_round3_2);
      
  }
   
   private void createButton(final String text, int gridx, int gridy, int gridwidth, Color color){
      JButton button = new JButton(text);
      button.setForeground(color);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.fill = GridBagConstraints.BOTH;
      
      add(button, gbc);
      
   }
   
   //creates a new button to be shown on wrestler 1's score paper
   //creates a new button to be shown on wrestler 2's score paper
   private void createScoreButton(final String details, final JPanel panel){
      JButton temp = new JButton(details);
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
}