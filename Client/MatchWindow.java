import javax.swing.*;
import java.awt.*;

public class MatchWindow extends JPanel{
   
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT =  600;
   
   public MatchWindow(){
      setPreferredSize(new Dimension(WINDOW_WIDTH + 200, WINDOW_HEIGHT + 100));
            
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{WINDOW_WIDTH / 16, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8};
      gbl.rowHeights = new int[]{WINDOW_HEIGHT / 2, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10, WINDOW_HEIGHT / 10};
      setLayout(gbl);
            
      JPanel scorePaper = new JPanel();
      scorePaper.setBackground(Color.BLACK);
      GridBagConstraints gbc_scorePaper = new GridBagConstraints();
      gbc_scorePaper.gridx = 1;
      gbc_scorePaper.gridy = 1;
      gbc_scorePaper.gridwidth = 3;
      gbc_scorePaper.fill = GridBagConstraints.BOTH;
      
      JPanel scorePaper_2 = new JPanel();
      scorePaper_2.setBackground(Color.BLACK);
      GridBagConstraints gbc_scorePaper_2 = new GridBagConstraints();
      gbc_scorePaper_2.gridx = 5;
      gbc_scorePaper_2.gridy = 1;
      gbc_scorePaper_2.gridwidth = 3;
      gbc_scorePaper_2.fill = GridBagConstraints.BOTH;
      
      JButton takedown = new JButton("Takedown");
      GridBagConstraints gbc_takedown = new GridBagConstraints();
      gbc_takedown.gridx = 1;
      gbc_takedown.gridy = 2;
      gbc_takedown.fill = GridBagConstraints.BOTH;
      
      JButton escape = new JButton("Escape");
      GridBagConstraints gbc_escape = new GridBagConstraints();
      gbc_escape.gridx = 2;
      gbc_escape.gridy = 2;
      gbc_escape.fill = GridBagConstraints.BOTH;
      
      JButton reversal = new JButton("Reversal");
      GridBagConstraints gbc_reversal = new GridBagConstraints();
      gbc_reversal.gridx = 3;
      gbc_reversal.gridy = 2;
      gbc_reversal.fill = GridBagConstraints.BOTH;
      
      JButton nearfall2 = new JButton("2 Pt. Nearfall");
      GridBagConstraints gbc_nearfall2 = new GridBagConstraints();
      gbc_nearfall2.gridx = 1;
      gbc_nearfall2.gridy = 3;
      gbc_nearfall2.fill = GridBagConstraints.BOTH;
      
      JButton nearfall3 = new JButton("3 Pt. Nearfall");
      GridBagConstraints gbc_nearfall3 = new GridBagConstraints();
      gbc_nearfall3.gridx = 2;
      gbc_nearfall3.gridy = 3;
      gbc_nearfall3.fill = GridBagConstraints.BOTH;
      
      JButton technicalViolation = new JButton("Technical Violation");
      GridBagConstraints gbc_tech_violation = new GridBagConstraints();
      gbc_tech_violation.gridx = 3;
      gbc_tech_violation.gridy = 3;
      gbc_tech_violation.fill = GridBagConstraints.BOTH;
      
      JButton stalling = new JButton("Stalling");
      GridBagConstraints gbc_stalling = new GridBagConstraints();
      gbc_stalling.gridx = 1;
      gbc_stalling.gridy = 4;
      gbc_stalling.fill = GridBagConstraints.BOTH;

      JButton bloodTime = new JButton("Blood Time");
      GridBagConstraints gbc_blood = new GridBagConstraints();
      gbc_blood.gridx = 2;
      gbc_blood.gridy = 4;
      gbc_blood.fill = GridBagConstraints.BOTH;
      
      JButton pin = new JButton("Pin");
      GridBagConstraints gbc_pin = new GridBagConstraints();
      gbc_pin.gridx = 3;
      gbc_pin.gridy = 4;
      gbc_pin.fill = GridBagConstraints.BOTH;
      
      JButton takedown_2 = new JButton("Takedown");
      GridBagConstraints gbc_takedown_2 = new GridBagConstraints();
      gbc_takedown_2.gridx = 5;
      gbc_takedown_2.gridy = 2;
      gbc_takedown_2.fill = GridBagConstraints.BOTH;
      
      JButton escape_2 = new JButton("Escape");
      GridBagConstraints gbc_escape_2 = new GridBagConstraints();
      gbc_escape_2.gridx = 6;
      gbc_escape_2.gridy = 2;
      gbc_escape_2.fill = GridBagConstraints.BOTH;
      
      JButton reversal_2 = new JButton("Reversal");
      GridBagConstraints gbc_reversal_2 = new GridBagConstraints();
      gbc_reversal_2.gridx = 7;
      gbc_reversal_2.gridy = 2;
      gbc_reversal_2.fill = GridBagConstraints.BOTH;

      JButton nearfall2_2 = new JButton("2 Pt. Nearfall");
      GridBagConstraints gbc_nearfall2_2 = new GridBagConstraints();
      gbc_nearfall2_2.gridx = 5;
      gbc_nearfall2_2.gridy = 3;
      gbc_nearfall2_2.fill = GridBagConstraints.BOTH;
      
      JButton nearfall3_2 = new JButton("3 Pt. Nearfall");
      GridBagConstraints gbc_nearfall3_2 = new GridBagConstraints();
      gbc_nearfall3_2.gridx = 6;
      gbc_nearfall3_2.gridy = 3;
      gbc_nearfall3_2.fill = GridBagConstraints.BOTH;
      
      JButton technicalViolation_2 = new JButton("Technical Violation");
      GridBagConstraints gbc_tech_violation_2 = new GridBagConstraints();
      gbc_tech_violation_2.gridx = 7;
      gbc_tech_violation_2.gridy = 3;
      gbc_tech_violation_2.fill = GridBagConstraints.BOTH;
      
      JButton stalling_2 = new JButton("Stalling");
      GridBagConstraints gbc_stalling_2 = new GridBagConstraints();
      gbc_stalling_2.gridx = 5;
      gbc_stalling_2.gridy = 4;
      gbc_stalling_2.fill = GridBagConstraints.BOTH;

      JButton bloodTime_2 = new JButton("Blood Time");
      GridBagConstraints gbc_blood_2 = new GridBagConstraints();
      gbc_blood_2.gridx = 6;
      gbc_blood_2.gridy = 4;
      gbc_blood_2.fill = GridBagConstraints.BOTH;
      
      JButton pin_2 = new JButton("Pin");
      GridBagConstraints gbc_pin_2 = new GridBagConstraints();
      gbc_pin_2.gridx = 7;
      gbc_pin_2.gridy = 4;
      gbc_pin_2.fill = GridBagConstraints.BOTH;
      
      add(scorePaper, gbc_scorePaper);
      add(scorePaper_2, gbc_scorePaper_2);
      
      //Wrestler1 Row 1
      add(takedown, gbc_takedown);
      add(escape, gbc_escape);
      add(reversal, gbc_reversal);      
      //Wrestler 1 Row 2
      add(nearfall2, gbc_nearfall2);
      add(nearfall3, gbc_nearfall3);
      add(technicalViolation, gbc_tech_violation);
      //Wrester 1 Row 3
      add(stalling, gbc_stalling);
      add(bloodTime, gbc_blood);
      add(pin, gbc_pin);
      
      //Wrester 2 Row 1    
      add(takedown_2, gbc_takedown_2);
      add(escape_2, gbc_escape_2);
      add(reversal_2, gbc_reversal_2);
      //Wrester 2 Row 2
      add(nearfall2_2, gbc_nearfall2_2);
      add(nearfall3_2, gbc_nearfall3_2);
      add(technicalViolation_2, gbc_tech_violation_2);
      //Wrester 2 Row 3
      add(stalling_2, gbc_stalling_2);
      add(bloodTime_2, gbc_blood_2);
      add(pin_2, gbc_pin_2);
   }
}