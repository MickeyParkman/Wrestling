import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parser{

   private Wrestler[][] wrestlers;
   int[] weightClasses = {106, 113, 120, 126, 132, 138, 145, 152, 160, 170, 182, 195, 220, 285};
   
   public Parser(Wrestler[][] wrestlers){
      this.wrestlers = wrestlers;
      getDirectory();
   }
   
   private void getDirectory(){
      JFileChooser explorer = new JFileChooser();
      explorer.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnValue = explorer.showOpenDialog(null);
      File directory = explorer.getSelectedFile();
      if(directory.list().length != 0){
         try{
            parse(directory);
         }catch(IOException e){
            e.printStackTrace();
         }
      }
   }
   
   public void parse(File directory) throws IOException{
      File[] files = directory.listFiles();      
      int numFiles = files.length;
      int[] numWrestlers = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      Team team = new Team(numFiles);
      Scanner scan;
      int weightClass = 0;
      for(int i = 0; i < numFiles; i++){
         String teamName = files[i].getName();
         scan = new Scanner(files[i]);
         for(int j = 0; scan.hasNextLine(); j++){
            String temp = scan.nextLine().trim();
            try{
               weightClass = getWeightClass(Integer.parseInt(temp));
            }catch(NumberFormatException e){
               wrestlers[weightClass][numWrestlers[weightClass]++] = new Wrestler(temp, teamName, true, 12);
            }
         }
      }
   }
   
   public int getWeightClass(int weight){
      for(int i = 0; i < weightClasses.length; i++){
         if(weightClasses[i] == weight){
            return i;
         }
      }
      return -1;
   }
}