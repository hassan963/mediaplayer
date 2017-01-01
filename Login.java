import dbconnect.*;
import player.*;
import player.historylist.JListExample;
import player.historylist.TrackHistory;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.swing.SwingUtilities;

class WindowSensorLogin extends WindowAdapter{
    public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
        System.exit(0);
    }
}
class MyFrameLogin extends Frame{
    public String msg;
    public TextField userID,passET;
    public Label message;
    public MyFrameLogin(){
        super("Login");
        msg="Window Created";
        
        message=new Label();
        Label userLabel=new Label("UserID");
        Label passLabel=new Label("Password");
        
        Button loginButton=new Button("LOGIN");
        Button signupButton=new Button("SIGNUP");
        
        userID=new TextField(30); passET=new TextField(30);
        
        add(userLabel); add(userID);
        add(passLabel); add(passET);
        
        
        add(loginButton); add(signupButton);
        
        //sending MyFrameLogin instance to  ButtoonSensor class
        ButtonSensorLogin bs=new ButtonSensorLogin(this);
        loginButton.addActionListener(bs);
        signupButton.addActionListener(bs);
        //deleteButton.addActionListener(bs);
        
        setSize(700,300);
        int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        
        setLocation((screenWidth-280)/2,120);
        
        setLayout(new FlowLayout());
    }
}
class UtilityLogin{
    
    //verifying credentials
    public void readLogin(MyFrameLogin frame){
        ResultSet rs=null;
        String password="";
        String user_id="";
        
        try{
            DataAccess da=new DataAccess(); //connecting with db
            
            String sql="select user_id, password from new_db.registration where user_id='"+frame.userID.getText()+"'";
            rs = da.getData(sql);  //returning Resultset 
            
            while(rs.next()){
                user_id=rs.getString("user_id");
                password = rs.getString("password");
                
                
            }
            
            da.close();
            
            if(frame.passET.getText().equals(password)){
                //calling MyTest frame that show Player Layout 
                MyTest myTest = new MyTest();
                myTest.display(user_id);
                frame.setVisible(false);

		//calling Filetest frame that show mp3 files from hd
                FileTest f = new FileTest();
                f.extract(new File("/home")); //scanning hd
                f.extract(new File("/mnt/B0280C7A280C41B8")); //scanning hd
                f.print(myTest);
                
            }else{
                System.out.println("password didn't match");
                JOptionPane.showMessageDialog(frame,"password didn't match");
            }
            
        }catch(SQLException ex){
            
        }
        
        
        
        
    }
    
}
class ButtonSensorLogin implements ActionListener{
    MyFrameLogin mf;
    UtilityLogin u;
    public ButtonSensorLogin(MyFrameLogin f){
        mf=f;
        u=new UtilityLogin();
    }
    public void actionPerformed(ActionEvent ae){
        boolean flag=true;
        String dn=mf.userID.getText();
        String pass = mf.passET.getText();
        if(dn.length()==0||pass.length()==0){
            JOptionPane.showMessageDialog(mf,"You must provide the credentials.");
            
            flag=false;
        }
        String s=ae.getActionCommand(); //getting title of button
        if(flag && s.equals("LOGIN")){
            u.readLogin(mf);  //calling Utilitylogin class method
            System.out.println("LoginE");
        }
        else if(s.equals("SIGNUP")){
            SignUp signUP = new SignUp();
            signUP.showFrame(); mf.setVisible(false);
        }
        
        
    }
}

public class Login{
    
    //calling this method from SignUp class
    public void show(){
        MyFrameLogin mf=new MyFrameLogin();
        mf.addWindowListener(new WindowSensorLogin());
        mf.setVisible(true);
        
    }
    public static void main(String str[]){
        MyFrameLogin mf=new MyFrameLogin();
        mf.addWindowListener(new WindowSensorLogin());
        mf.setVisible(true);
    }
}
