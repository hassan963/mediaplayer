package player;
import player.historylist.JListExample;
import player.historylist.TrackHistory;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import java.io.BufferedInputStream;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.*;

public class MyTest{
    
    private Player player;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private long pauseTime;
    private long songtotalTime;
    private String songPath;
    private static MyTest myTest;
    private ArrayList<String> paths = new ArrayList<>();
    private int count = 0;
    private String userID;
    
    private TrackHistory trackHistoy = new TrackHistory();
    
    
    public void stop(){
        if(player != null){
            player.close();
            pauseTime = 0;
            songtotalTime = 0;
        }
        
    }
    
    
    public void pause(){
        
        if(player != null){
            
            try{
                
                pauseTime = fis.available();
                player.close();
                
            }catch(Exception ex){
                
            }
            
            
        }
        
    }
    // opening file from desktop
    public void open(){
        
        
        FileFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3", "mpeg3");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(filter);
        
        int returnVal = fileChooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION){
            
            stop();
            File myFile = fileChooser.getSelectedFile();
            String song = myFile + "";
            
            String name = fileChooser.getSelectedFile().getName();
            
            play(song);
        }
        
        
    }
    
    
    
    
    public void resume(){
        
        try{
            fis = new FileInputStream(songPath);
            bis = new BufferedInputStream(fis);
            
            player = new Player(bis);
            fis.skip(songtotalTime - pauseTime);
            
        }catch(Exception ex){
            
        }
        //catch(IOException  ex){
        //}
        
        new Thread(){
            
            @Override
            public void run(){
                
                try{
                    player.play();
                    
                }catch(JavaLayerException ex){
                }
            }
        }.start();
        
        
    }
    
    
    
    public void play(String path){
        paths.add(path);
        
        fis = null;
        
        
        try{
            // create new file input stream
            fis = new FileInputStream(path);
            
            bis = new BufferedInputStream(fis);
            
            player = new Player(bis);
            
            
            // available bytes
            songtotalTime = fis.available();
            songPath = path + "";
            
            
            
        }catch(Exception ex){
            // if an I/O error occurs
            ex.printStackTrace();
        }new Thread(){
            
            @Override
            public void run(){
                
                try{
                    
                    player.play();
                    trackHistoy.readTrack(path, userID);
                }catch(JavaLayerException ex){
                }
            }
        }.start();
        
    }
    
    
    
    //calling this method from Login class
    public void display(String userID){
        //myTest = new MyTest();
        this.userID = userID;
        MyFramePlay mf=new MyFramePlay(this, userID);
        
        mf.addWindowListener(new WindowSensorPlay());
        mf.setVisible(true);
    }
    //returning current path
    public String ready(int count){return paths.get(count);}
    //playing next track
    public void next(){
        
        if(count<paths.size()-1){
            count++;
            System.out.println("next: "+count+paths.size());
        }
        
        stop();
        
        playTrack();
        
    }
    
    //playing previous track
    public void previous(){
        if(count<paths.size() && count>=1){
            count--;
            System.out.println("next: "+count);
        }
        stop();
        
        playTrack();
    }
    
    //playing current track
    public void playTrack(){
        
        fis = null;
        //int available = 0;
        int i=0;
        
        try{
            // create new file input stream
            fis = new FileInputStream(ready(count));
            
            bis = new BufferedInputStream(fis);
            
            player = new Player(bis);
            
            
            // available bytes
            songtotalTime = fis.available();
            songPath = ready(count) + "";
            
           
        }catch(Exception ex){
            // if an I/O error occurs
            ex.printStackTrace();
        }new Thread(){
            
            @Override
            public void run(){
                
                try{
                    
                    player.play();
                    trackHistoy.readTrack(ready(count), userID);
                }catch(JavaLayerException ex){
                }
            }
        }.start();
        
        
    }
    
    
}
