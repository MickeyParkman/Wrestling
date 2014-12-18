import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.WindowConstants;
public class MatchWindow extends JFrame{
   
   private static final int WINDOW_WIDTH = 800;
   private static final int WINDOW_HEIGHT =  600;
   
   public MatchWindow(){
      setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
      setResizable(false);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setVisible(true);
   }
   
   public static void main(String[] args){
      new MatchWindow();
   }
   
}