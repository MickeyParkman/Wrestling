import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
public class SeedingMeeting extends JFrame{
   
   private Wrestler[][] theWrestlers;
   private int numWrestlers;
   private final int WINDOW_WIDTH = 800;
   private final int WINDOW_HEIGHT = 600;
   private static final int[] weights = {106,113,120,126,132,138,145,152,160,170,182,195,220,285};
   private Font font = new Font("Courier New", Font.PLAIN, 16);
   private int currentWeight = 0;
   
   private JPanel currPanel;
   private JScrollPane scroll;
   
   public SeedingMeeting(Wrestler[][] wrestlers, int numWrestling){
      this.theWrestlers = wrestlers;
      this.numWrestlers = numWrestling;
      final WrestlerDisplay[] panels = new WrestlerDisplay[wrestlers.length];
      for(int i = 0; i < panels.length; i++){
         panels[i] = new WrestlerDisplay(wrestlers[i]);
      }
      currPanel = panels[0];
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      
      GridBagLayout gbl = new GridBagLayout();
      gbl.columnWidths = new int[]{WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8, WINDOW_WIDTH / 8};
      gbl.rowHeights = new int[]{WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 8, WINDOW_HEIGHT / 16, WINDOW_HEIGHT / 8};
      setLayout(gbl);
      
      GridBagConstraints gbc = new GridBagConstraints();
      
      final JLabel weightDisplay = new JLabel(String.valueOf(weights[0]));
      weightDisplay.setHorizontalAlignment(JLabel.CENTER);
      weightDisplay.setFont(new Font("Courier New", Font.PLAIN, 60));
      gbc.gridx = 1;
      gbc.gridy = 0;
      gbc.gridwidth = 6;
      gbc.gridheight = 2;
      gbc.fill = GridBagConstraints.BOTH;
      
      add(weightDisplay, gbc);
      
      final JPopupMenu weightMenu = new JPopupMenu();
      weightMenu.setPreferredSize(new Dimension(WINDOW_WIDTH / 8 + 21, WINDOW_HEIGHT * 14 / 16));
      final JButton weightButton = new JButton("Switch Weights");
      weightButton.setFocusPainted(false);
      final JFrame temp = this;
      weightButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            weightMenu.show(temp, weightButton.getX() + 4, weightButton.getY() + 26);
         }
      });
      for(int i = 0; i < weights.length; i++){
         JMenuItem item = new JMenuItem(String.valueOf(weights[i]));
         //item.setMaximumSize(new Dimension(weightButton.getWidth(), weightButton.getHeight()));
         final int itemIndex = i;
         item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               currPanel = panels[itemIndex];
               scroll.setViewportView(currPanel);               
               currentWeight = weights[itemIndex];
               weightDisplay.setText(String.valueOf(currentWeight));
            }
         });
         weightMenu.add(item);
      }
      
      gbc.gridx = 3;
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      gbc.gridheight = 1;
      gbc.fill = GridBagConstraints.NONE;
      add(weightButton, gbc);
      
      gbc.gridx = 1;
      gbc.gridy = 4;
      gbc.gridwidth = 6;
      gbc.gridheight = 4;
      gbc.fill = GridBagConstraints.BOTH;
      scroll = new JScrollPane(currPanel);
      add(scroll, gbc);
      
      JButton createBrackets = new JButton("Create Brackets");
      createBrackets.setFocusPainted(false);
      createBrackets.setFont(font);
      createBrackets.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            new Tournament(theWrestlers, numWrestlers);
         }
      });
      gbc.gridy = 9;
      gbc.gridheight = 1;
      
      add(createBrackets, gbc);
      
      setVisible(true);
   }
   
   private class WrestlerDisplay extends JPanel{
      public WrestlerDisplay(final Wrestler[] wrestlers){
         
         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         setDoubleBuffered(true);
         
         final JButton[] buttons = new JButton[wrestlers.length];
         final int buttonHeight = WINDOW_HEIGHT / 8;
         
         for(int i = 0; i < wrestlers.length; i++){
            final JButton wrestler;
            if(wrestlers[i] == null)
               wrestler = new JButton(String.format("%-20s (%s)","BYE", ""));
            else
               wrestler = new JButton(String.format("%-20s (%s)",wrestlers[i].name, wrestlers[i].teamName));
            wrestler.setHorizontalAlignment(JButton.LEFT);
            wrestler.setAlignmentX(Component.CENTER_ALIGNMENT);
            wrestler.setMaximumSize(new Dimension(WINDOW_WIDTH * 6 / 8, buttonHeight));
            wrestler.setFont(font);
            wrestler.setFocusPainted(false);
            buttons[i] = wrestler;
            wrestler.addMouseMotionListener(new MouseMotionAdapter(){
               public void mouseDragged(MouseEvent e){
                  int newY = wrestler.getY() + e.getY();
                  if(newY < (buttons.length - 1) * wrestler.getHeight() + 5 && newY > -1){
                     wrestler.setLocation(0, newY);
                     int buttonIndex = find(buttons, wrestler);
                     int currentIndex = newY / wrestler.getHeight();
                     if(currentIndex != buttonIndex){
                        JButton temp = buttons[buttonIndex];
                        buttons[buttonIndex] = buttons[currentIndex];
                        buttons[currentIndex] = temp;
                        
                        Wrestler tempWrestler = wrestlers[buttonIndex]; 
                        wrestlers[buttonIndex] = wrestlers[currentIndex];
                        wrestlers[currentIndex] = tempWrestler;                  
                        updateButtons(buttons);                    
                     }
                  }              
               }
            });
            
            wrestler.addMouseListener(new MouseAdapter(){
                public void mouseReleased(MouseEvent e){
                  int buttonIndex = find(buttons, wrestler);
                  wrestler.setLocation(0, buttonIndex * wrestler.getHeight());
               }
            });
            add(wrestler);
         }
         
      }
      
      private int find(JButton[] buttons, JButton toFind){
         for(int i = 0; i < buttons.length; i++){
            if(buttons[i].equals(toFind)){
               return i;
            }
         }
         return -1;
      }
      
      private void updateButtons(JButton[] buttons){
         for(int i = 0; i < buttons.length; i++){
            remove(buttons[i]);
            add(buttons[i]);
         }
         updateUI();
      }
   }
   
}