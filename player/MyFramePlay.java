package player;
import player.historylist.JListExample;
import player.historylist.TrackHistory;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

class WindowSensorPlay extends WindowAdapter{
    
    
    public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
        System.exit(0);
    }
}
class MyFramePlay extends Frame{
    public String msg;
    
    
    static MyTest myTest;
    
    public MyFramePlay(MyTest myTest, String userID){
        super("CoolPlay");
        msg="Window Created";
        this.myTest = myTest;
        
        
        Button playButton=new Button("PLAY");
        
        Button pauseButton=new Button("PAUSE");
        Button resumeButton=new Button("RESUME");
        
        Button stopButton=new Button("STOP");
        Button openButton=new Button("OPEN");
        Button listButton=new Button("NEXT");
        Button previousButton=new Button("PREVIOUS");
        
        Button historyButton=new Button("HISTORY");
        
        
        
        add(playButton);
        add(pauseButton);
        add(stopButton);
        add(resumeButton);
        add(openButton);
        add(listButton);
        add(previousButton);
        add(historyButton);
        
        //sending this -> MyFramePlay instance, myTest -> to invoke all the method in MyTest class like play(), pause(),.., userID -> session
        ButtonSensor bs=new ButtonSensor(this, myTest, userID);
        playButton.addActionListener(bs);
        pauseButton.addActionListener(bs);
        stopButton.addActionListener(bs);
        resumeButton.addActionListener(bs);
        openButton.addActionListener(bs);
        listButton.addActionListener(bs);
        previousButton.addActionListener(bs);
        historyButton.addActionListener(bs);
        
        setSize(280,300);
        int screenWidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        
        
        
        //setLocation((screenWidth-280)/2,120);
        setLocation(0,0);
        setLayout(new FlowLayout());
    }
}

class ButtonSensor implements ActionListener{
    
    
    MyFramePlay mf;
    static MyTest myTest;
    String userID;
    public ButtonSensor(MyFramePlay f, MyTest myTest, String userID){
        mf=f;
        this.myTest = myTest;
        this.userID = userID;
    }
    public void actionPerformed(ActionEvent ae){
        
        
        String s=ae.getActionCommand();
        
        if(s.equals("PLAY")){
          
            myTest.play("/home/hassan/Downloads/Chaandaniya.mp3");
            System.out.println("PLAY pressed");
        }
        else if(s.equals("PAUSE")){
            myTest.pause();
            System.out.println("PAUSE pressed");
        }
        else if(s.equals("STOP")){
         
            myTest.stop();
            System.out.println("STOP pressed");
        }
        else if(s.equals("RESUME")){
         
            myTest.resume();
            System.out.println("RESUME pressed");
        }
        else if(s.equals("OPEN")){
          
            myTest.open();
            System.out.println("OPEN pressed");
        }
        else if(s.equals("NEXT")){
         
            myTest.next();
            System.out.println("NEXT pressed");
        }
        else if(s.equals("PREVIOUS")){
            
            myTest.previous();
            System.out.println("PREVIOUS pressed");
        }
        else if(s.equals("HISTORY")){
            //showing track history for specific user
            JListExample jList = new JListExample(myTest, userID);
            jList.setVisible(true);
            System.out.println("HISTORY pressed");
        }
        
    }
    
    
    
}


