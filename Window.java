import java.util.ArrayList;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.*;

public class Window extends JFrame{
    private JTabbedPane tabbedpane = new JTabbedPane();

    public Window(int x, int y){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Points");
        this.setSize(new Dimension(x,y));
        this.setResizable(false);

        this.getContentPane().setBackground(new Color(211, 211, 211));

        tabbedpane.setTabPlacement(JTabbedPane.TOP);
    }

    public void setUp(ArrayList<Member> list){
        MemberTab mTab = new MemberTab(list, this);
        addTab("Members", mTab, 0);
        
        this.add(tabbedpane);
    }

    public void addTab(String title, JPanel p, int index){
        try{
            tabbedpane.setComponentAt(index, p);
        } catch (Exception err){
            try{
                tabbedpane.addTab(title, p);
            } catch (Exception er){}
        }
        finally{
            update();
        }
        
    }
    private void update(){
        this.revalidate();
    }

}

class MemberTab extends JPanel{
    Window window;
    ArrayList<Member> list;
    JComboBox dropdown;
    Button submit = new Button("Submit");

    public MemberTab(ArrayList<Member> members, Window window){
        this.list = members;
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
        // Setting up dropdown values
        String[] members = new String[list.size()];

        ArrayList<String> memberNames = new ArrayList<String>();
        for(Member m: list){
            memberNames.add(m.name());
        }
        
        members = memberNames.toArray(members);
        dropdown = new JComboBox(members);
        
        this.add(dropdown);

        // Setting up submit button
        submit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    int index = (int) dropdown.getSelectedIndex();
                    
                    Member selectedMember = list.get(index);

                    MonthTab monthtab = new MonthTab(selectedMember, window);
                    window.addTab("Months", monthtab, 1);
                }
            });
        this.add(submit);
    }
}

class MonthTab extends JPanel{
    Window window;
    ArrayList<Month> months;
    JComboBox dropdown;
    Button submit = new Button("Submit");

    public MonthTab(Member m, Window window){
        this.months = m.getMonths();
        this.window = window;

        this.setBackground(new Color(150, 150, 150));
        
        setUp();
    }

    private void setUp(){
        // Setting up dropdown
        String[] array = new String[months.size()];

        ArrayList<String> convert = new ArrayList<String>();
        for(Month m:months){
            convert.add(m.getName());
        }
        array = convert.toArray(array);
        dropdown = new JComboBox(array);
        this.add(dropdown);

        // Setting up button
        submit.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    int index = (int) dropdown.getSelectedIndex();
                    
                    Month selectedMonth = months.get(index);

                    EventTab eventTab = new EventTab(selectedMonth);
                    window.addTab("Events", eventTab, 2);
                }
            });
        this.add(submit);
    }
}

class EventTab extends JPanel{
    ArrayList<Event> events;

    public EventTab(Month mon){
        this.events = mon.getEvents();
        this.setLayout(new GridLayout(events.size(), 1));

        setUp();
    }

    private void setUp(){
        if(events.size() == 0){
            this.add(new Label("No events found."));
        } else{
            for(Event e: events){
                String event = e.getEvent();
    
                this.add(new Label(event));
            }
        }
        
    }
}