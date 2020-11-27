package src;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame{
    LabelPanel label = new LabelPanel();
    EventPanel events = new EventPanel();
    MemberPanel members = new MemberPanel();
    InfoPanel info = new InfoPanel();

    public Window(int x, int y){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setTitle("Test");
        this.setSize(new Dimension(x,y));
        this.setResizable(false);
        
        this.getContentPane().setBackground(new Color(211, 211, 211));
    }

    public void panelSetup(){
        // Event panel set up
        GetEvents pull = new GetEvents();
        events.initialize(pull.pull(), this);

        // Adding each panel
        this.add(label, BorderLayout.PAGE_START);
        this.add(events, BorderLayout.LINE_START);
        this.add(members, BorderLayout.CENTER);
        this.add(info, BorderLayout.LINE_END);
    }

    public void update(){
        this.revalidate();
    }

}

class LabelPanel extends JPanel{
    public LabelPanel(){
        this.setBackground(new Color(150, 150, 150));
        this.setLayout(new GridLayout(1, 3));
    }
    public void addTitle(String s){
        this.removeAll();
        this.add(new Label(s));
    }
}

class EventPanel extends JPanel{
    
    public void initialize(ArrayList<Event> list, Window frame){
        this.setBackground(new Color(220, 220, 220));
        this.setLayout(new GridLayout(list.size()+1,1));
        
        frame.events.removeAll();
        frame.label.addTitle("Events");

        for(Event n : list){
            
            Button b = new Button(n.name);
            b.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    MemberPanel.update(n, frame);
                    frame.update();
                }
            });

            this.add(b);
        }

    }

}

class MemberPanel extends JPanel{
    public MemberPanel(){
        this.setMinimumSize(new Dimension(400, 100));
        this.setBackground(new Color(250, 250, 250));
        this.setLayout(new GridLayout(3, 1));
    }

    public static void update(Event e, Window frame){
        
        frame.members.removeAll();
        frame.label.addTitle("Member List");

        for(Member m : e.memberList){
            Button b = new Button(m.name);
            b.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    InfoPanel.update(m, frame);
                    frame.update();
                }
            });

            frame.members.add(b);
        }

    }

}

class InfoPanel extends JPanel{
    public InfoPanel(){
        this.setMinimumSize(new Dimension(300, 100));
        this.setBackground(new Color(220, 220, 220));
        this.setLayout(new GridLayout(3, 1));
    }

    public static void update(Member m, Window frame){
        frame.info.removeAll();

        frame.info.add(new Label(m.name));
        frame.info.add(new Label(getPoints(m)));
        frame.info.add(new Label(getCheckIn(m)));
    }

    private static String getPoints(Member m){
        String points =  Integer.toString(m.points);
        return "Points: " + points;
    }

    private static String getCheckIn(Member m){
        if(m.checkIn == true){
            return "Checked in: " + "Yes";
        } else return "Checked in: " + "No";
    }
}
