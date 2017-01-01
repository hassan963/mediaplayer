package dbconnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DataAccess extends DBPass {
        
    Statement stmt;
    Connection conn;
    ResultSet rs;
    
    public DataAccess(){
    
        
        try{
            Class.forName(getJDBC_DRIVER());
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(getDB_URL(),getUSER(),getPASS()); //getting connecton
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void close()throws SQLException{
        if(rs!=null)rs.close();
        if(rs!=null)stmt.close();
    }
    //getting data from db
    public ResultSet getData(String query) {
        try{
            stmt = conn.createStatement();
            rs= stmt.executeQuery(query);
            //System.out.println("Info from DB");
        }
        catch(Exception ex){
            System.out.println("DB Read Error !");
            //ex.printStackTrace();
        }
        return rs;
    }
    //inserting data in db
    public int updateDB(String sql){
        int numOfRowsUpdated=0;
        try{
            stmt = conn.createStatement();
            numOfRowsUpdated=stmt.executeUpdate(sql);
            System.out.println(numOfRowsUpdated+" row(s) updated");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numOfRowsUpdated;
    }
    //updating track_count
    public int updateTrackData(String sql,int track_count,String timeStamp,String track_name,String userID){
        int numOfRowsUpdated=0;
        try{
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            
            preparedStmt.setInt   (1, track_count);
            preparedStmt.setString(2, timeStamp);
            preparedStmt.setString(3, track_name);
            preparedStmt.setString(4, userID);
          
            preparedStmt.executeUpdate();
            preparedStmt.close();
            
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numOfRowsUpdated;
    }
    
}
