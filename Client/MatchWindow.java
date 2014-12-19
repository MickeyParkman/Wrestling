import javax.swing.*;
import java.awt.*;

public class MatchWindow extends JFrame{
   
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT =  600;
   
   public MatchWindow(){
      setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
      setResizable(false);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setVisible(true);
      
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{WINDOW_WIDTH / 16, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 16};
      gbl.rowHeights = new int[]{WINDOW_HEIGHT / 2, WINDOW_HEIGHT / 5, WINDOW_HEIGHT / 5, WINDOW_HEIGHT / 5, WINDOW_HEIGHT / 5, WINDOW_HEIGHT / 5};
      setLayout(gbl);
      
      JButton takedown = new JButton("Takedown");
      GridBagConstraints gbc_takedown = new GridBagConstraints();
      gbc_takedown.gridx = 1;
      gbc_takedown.gridy = 3;
      
      add(takedown, gbc_takedown);
   }
   
   public static void main(String[] args){
      new MatchWindow();
   }
   
}