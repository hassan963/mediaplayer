package player.historylist;
import dbconnect.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.*;


import java.util.Date; //for getting data
import java.text.SimpleDateFormat; //for getting data
import java.util.Calendar; //for getting data

public class TrackHistory{
    
    private ArrayList<String> myList = new ArrayList<>();
    
    //getting list for specific user's playlist
    public ArrayList<String> readTrackHistory(String userID){
        ResultSet rs=null;
        String track_name="";
        int track_count = 0;
        try{
            DataAccess da=new DataAccess();
            String sql="select * from new_db.music_track_history where user_id = '"+userID+"'";
            System.out.println(sql);
            rs = da.getData(sql);
            
            while(rs.next()){
                track_name = rs.getString("track_name");
                myList.add(track_name);
                
            }
            
            da.close();
            
            
        }catch(SQLException ex){
            
        }
        
        
        return myList;
        
    }
    
    
    
    //getting track name and track count for specific user with specific name to update the count
    public void readTrack(String track_name, String userID){
        ResultSet rs=null;
        String track_match="";
        int track_count = 0;
        
        
        try{
            DataAccess da=new DataAccess();
            String sql="select track_name,track_count from new_db.music_track_history WHERE track_name = '"+track_name+"' AND user_id = '"+userID+"'";
            
            
            System.out.println(sql);
            rs = da.getData(sql);
            
            while(rs.next()){
                track_match = rs.getString("track_name");
                track_count = rs.getInt("track_count");
                
                
            }
            
            da.close();
            
            if(track_match.equals(track_name)){
                updateTrack(track_name, track_count, userID);
            }else{
                insertTrack(track_name, userID);
            }
            
        }catch(SQLException ex){
            
        }
        
        
        
        
    }
    //updating the count for specific track
    public void updateTrack(String track_name, int track_count, String userID){
        track_count++;
        String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        String sql="UPDATE new_db.music_track_history SET track_count = ?, timeStamp = ? WHERE track_name = ? AND user_id = ?";
        System.out.println(sql);
        
        
        
        try{
            DataAccess da=new DataAccess();
            
            
            
            
            da.updateTrackData(sql,track_count,timeStamp,track_name,userID);
            
            da.close();
            System.out.println(sql);
            
        }catch(SQLException ex){
            
        }
        
    }
    
    //inserting track after playing track
    public void insertTrack(String track_name, String userID){
        
        String timeStamp = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(Calendar.getInstance().getTime());
        try{
            DataAccess da=new DataAccess();
            
            
            String sql="insert into new_db.music_track_history (track_name,track_count,timestamp,user_id) values('"+track_name+"',1,'"+timeStamp+"','"+userID+"')";
            
            da.updateDB(sql);
            
            da.close();
            System.out.println(sql);
            
        }catch(SQLException ex){
            
        }
        
    }
    
    
    
    
}
