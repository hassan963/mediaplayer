package dbconnect;
import java.lang.*;
/*
	USING THIS CLASS TO STORE ALL THE DB CONSTANT
*/
public abstract class DBPass {
    
    
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/new_db?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "1234";
    
    public static String getJDBC_DRIVER(){return JDBC_DRIVER;}
    public static String getDB_URL(){return DB_URL;}
    public static String getUSER(){return USER;}
    public static String getPASS(){return PASS;}
    
    
}
