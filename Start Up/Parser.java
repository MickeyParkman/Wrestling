import javax.swing.*;

public class Parser{

   private Wrestler[][] wrestlers;
   
   public Parser(Wrestler[][] wrestlers){
      this.wrestlers = wrestlers;
      getDirectory();
   }
   
   private void getDirectory(){
      JFileChooser explorer = new JFileChooser();
      explorer.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      File directory = explorer.getSelectedFile();
      if(directory.list().length != 0){
         parse(directory);
      }
   }
   
   public void parse(File directory){
      int numFiles = directory.list.length;
      Team team = new Team(numFiles);
      
   }
   
   
}