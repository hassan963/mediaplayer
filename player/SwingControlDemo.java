package player;
import player.historylist.JListExample;
import player.historylist.TrackHistory;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
mp3 list class with click

*/


public class SwingControlDemo extends JFrame {
    
    private JList<String> countryList;
    
    static MyTest myTest;
    
    ArrayList<String> myList; //= new ArrayList<>();
    
    public SwingControlDemo(ArrayList<String> myList, MyTest myTest) {
        
        
        
        this.myList = myList;
        this.myTest = myTest;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
	//converting ArrayList to DefaultListModel
        for(String s : myList){
            listModel.addElement(s);
        }
     
       
        
        //create the list
        countryList = new JList<>(listModel);
        
        countryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = countryList.getSelectedValuesList();
                    String s = selectedValuesList.toString();
                    s = s.substring(1, s.length()-1);
                    myTest.stop();
                    myTest.play(s);
                    System.out.println(s);
                }
            }
        });
        
        
        //ListModel model = new DefaultListModel();
        add(new JScrollPane(countryList));
        
        
        
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MP3 files");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }
    
    
    
    
}
