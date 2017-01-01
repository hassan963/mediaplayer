package player;
import player.historylist.JListExample;
import player.historylist.TrackHistory;
import javax.swing.DefaultListModel;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.swing.SwingUtilities;

public class FileTest{
    
    ArrayList<String> myList = new ArrayList<>();
    int count = 0;
    
    //scanning hd
    public  void extract(File dir) {
        File l[] = dir.listFiles();
        
        
        if (l == null) {
            
            return;
        }
        
        for (File x : l) {
            if (x.isDirectory())
                extract(x);
            if (x.isHidden() || !x.canRead())
                continue;
            else if (x.getName().endsWith(".mp3")){
                //listModel.add(x.getAbsolutePath());
                myList.add(x.getAbsolutePath());  
                count++;
                System.out.println(x.getName());//name should be included in path
            }
            //System.out.println(x.getName());//name should be included in path
        }
    }
    //calling this method from Login class to show the list
    public void print(MyTest myTest){
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingControlDemo(myList,myTest);
            }
        });
        
        
    }
    
    
    
}
