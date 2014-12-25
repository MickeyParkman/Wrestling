import javax.swing.JFrame;
import javax.swing.WindowConstants;
public class Start{
   public Start(){
      JFrame frame = new JFrame("Wrestling");
      frame.setContentPane(new MatchWindow("Michael", "David"));
      frame.pack();
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
   
   public static void main(String[] args){
      new Start();
   }
}