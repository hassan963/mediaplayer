import dbconnect.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.SQLException;

class WindowSensor extends WindowAdapter{
    public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
        System.exit(0);
    }
}
class RegisFrame extends Frame{
    public String msg;
    public TextField nameET,userID,passET;
    public Label message;
    public RegisFrame(){
        super("Registration");
        msg="Window Created";
        
        message=new Label();
        Label nameLabel=new Label("Name");
        Label userLabel=new Label("USERID");
        Label passLabel=new Label("Password");
        
        Button signUPBTN=new Button("SIGNUP");
        Button loginBTN=new Button("LOGIN");
        
        nameET=new TextField(50); userID=new TextField(30); passET=new TextField(30);
        
        add(nameLabel);add(nameET);
        add(userLabel);add(userID);
        add(passLabel);add(passET);
        
        add(signUPBTN);
        add(loginBTN);
        
        //sending RegisFrame instance to ButtonSensorSign class
        ButtonSensorSign bs=new ButtonSensorSign(this);
        signUPBTN.addActionListener(bs);
        loginBTN.addActionListener(bs);
        
        
        setSize(800,250);
        int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        
        setLocation((screenWidth-280)/2,120);
        setLayout(new FlowLayout());
    }
}
class UtilitySign{
    //int count = 2;
    
    //inserting user credentials to db
    public void insertUser(RegisFrame frame){
        try{
            DataAccess da=new DataAccess();
            
            String sql="insert into new_db.registration values('"+frame.nameET.getText()+"','"+frame.userID.getText()+"','"+frame.passET.getText()+"')";
            
            da.updateDB(sql);
            //count++;
            da.close();
            System.out.println(sql);
            
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
    }
}
class ButtonSensorSign implements ActionListener{
    RegisFrame mf;
    UtilitySign u;
    public ButtonSensorSign(RegisFrame f){
        mf=f;
        u=new UtilitySign();
    }
    public void actionPerformed(ActionEvent ae){
        boolean flag=true;
	
	//getting Textfield data 
        String nameET = mf.nameET.getText();
        String userID = mf.userID.getText();
        String passET = mf.passET.getText();

        //checking Textfield is empty 
        if(nameET.length() == 0 || userID.length() == 0 || passET.length() == 0){
            JOptionPane.showMessageDialog(mf,"You must provide the info.");
            
            flag=false;
        }
        String s=ae.getActionCommand(); //getting the title of buttons
        if(flag && s.equals("SIGNUP")){
            //calling UtilitySign class method 
            u.insertUser(mf);
        }
        else if(s.equals("LOGIN")){
            //showing Login frame
            Login l = new Login();
            l.show();
            mf.setVisible(false);
            
        }
        
        System.out.println("button pressed");
    }
}

public class SignUp{
    //calling this method from Login class to show SignUp frame
    public void showFrame(){
        RegisFrame mf=new RegisFrame();
        mf.addWindowListener(new WindowSensor());
        mf.setVisible(true);
    }
    
    
    public static void main(String str[]){
        RegisFrame mf=new RegisFrame();
        mf.addWindowListener(new WindowSensor());
        mf.setVisible(true);
    }
}
