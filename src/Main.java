package src;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main{
    public static void main(String args[]){
        Window frame = new Window(700, 700);
        
        frame.panelSetup();
        frame.update();
    }

}

class Window extends JFrame{
    JPanel top = new JPanel();
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
        ArrayList<Event> eList = new ArrayList<>();
        String[] c = {"Josh", "Josh 2"};
        String[] d = {"Meilin", "Meilin 2"};
        eList.add(new Event("Event 1", c));
        eList.add(new Event("Event 2", d));
        events.initialize(eList, this);

        // Adding each panel
        this.add(top, BorderLayout.PAGE_START);
        this.add(events, BorderLayout.LINE_START);
        this.add(members, BorderLayout.CENTER);
        this.add(info, BorderLayout.LINE_END);
    }

    public void update(){
        this.setVisible(false);
        this.setVisible(true);
    }

}

class EventPanel extends JPanel{
    
    public void initialize(ArrayList<Event> list, Window frame){
        this.setBackground(new Color(220, 220, 220));
        this.setLayout(new GridLayout(list.size(),1));

        for(Event n : list){
            Button b = new Button(n.name);
            b.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    MemberPanel.update(n, frame);
                    System.out.println("Button " + n.name + " pressed");
                    frame.update();
                }
            });

            this.add(b);
        }

    }
}

class MemberPanel extends JPanel{
    public MemberPanel(){
        this.setBackground(new Color(250, 250, 250));
        this.setLayout(new GridLayout(3, 1));
    }

    public static void update(Event e, Window frame){
        
        for(String m : e.chairs){
            Button b = new Button(m);
            b.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    Member test = new Member("Joshua", "Lin");
                    InfoPanel.update(test, frame);
                    System.out.println("Button " + " pressed");
                    frame.update();
                }
            });

            frame.members.add(b);
        }

    }

}

class InfoPanel extends JPanel{
    public InfoPanel(){
        this.setBackground(new Color(220, 220, 220));
        this.setLayout(new GridLayout(3, 1));
    }

    public static void update(Member m, Window frame){
        frame.info.add(new Label(m.name));
    }

}

class Event{
    String name;
    ArrayList<String> chairs = new ArrayList<>();
    String description;
    ArrayList<Member> memberList;
    
    public Event(String a, String[] b){
        this.name = a;
        Collections.addAll(chairs, b);
    }

    public void chairlist(){
        System.out.println(chairs);
    }

}

class Member{
    String name;
    int points = 0;
    boolean checkIn = false;

    Member(String first, String last){
        name = first + " " + last; 
    }
}