package player.historylist;
import player.*;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;

public class JListExample extends JFrame {
    
    private JList<String> countryList;
    private static MyTest myTest;
    private ArrayList<String> myList;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public JListExample(MyTest myTest, String userID) {
        //create the model and add elements
        this.myTest = myTest;
        
        TrackHistory trackHistoy = new TrackHistory();
        
        this.myList = trackHistoy.readTrackHistory(userID);

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
        
        add(new JScrollPane(countryList));
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Heard sond with ::: cooLPlay");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
