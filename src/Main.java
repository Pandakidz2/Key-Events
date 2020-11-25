package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main{
    public static void main(String args[]){
        Window frame = new Window(700, 700);
        
        frame.panelSetup();
        frame.east.add(new Label("JP3 panel"));

        Button button = new Button("Button!");
        frame.top.add(button);

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                frame.events.setBackground(Color.RED);
                frame.center.setBackground(Color.BLACK);
                frame.east.setBackground(Color.BLUE);
            }
        });
        
        frame.add(frame.top, BorderLayout.PAGE_START);
        frame.add(frame.events, BorderLayout.LINE_START);
        frame.add(frame.center, BorderLayout.CENTER);
        frame.add(frame.east, BorderLayout.LINE_END);

        frame.setVisible(true);
    }

}

class Window extends JFrame{
    // static final long serialVersionUID = asjdkasdlasdl12312390;
    JPanel top = new JPanel();
    EventPanel events = new EventPanel();
    JPanel center = new JPanel();
    JPanel east = new JPanel();

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
        eList.add(new Event("Test1", c));
        eList.add(new Event("Test2", c));
        events.initialize(eList);
        
        // Member panel set up

        // Info panel set up
    }

    public void update(){
        this.setVisible(false);
        this.setVisible(true);
    }

}

class EventPanel extends JPanel{
    
    public void initialize(ArrayList<Event> list){

        this.setLayout(new GridLayout(list.size(),1));

        for(Event n : list){
            this.add(new Label(n.name));
        }

    }
}

class Event{
    String name;
    ArrayList<String> chairs = new ArrayList<>();
    String description;
    ArrayList<member> memberList;
    
    public Event(String a, String[] b){
        this.name = a;
        Collections.addAll(chairs, b);
    }

    public void chairlist(){
        System.out.println(chairs);
    }

}

class member{
    String firstName;
    String lastName;
    int points;
    boolean checkIn = false;
}
/*
class memberPanel{

}

class infoPanel{

}
*/