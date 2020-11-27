package src;

import java.util.ArrayList;
import java.util.Collections;

public class Event{
    String name;
    ArrayList<String> chairs = new ArrayList<>();
    String description;
    ArrayList<Member> memberList = new ArrayList<>();
    
    public Event(String a, String[] b){
        this.name = a;
        Collections.addAll(chairs, b);
    }

    public void chairlist(){
        System.out.println(chairs);
    }

}

// Will eventually pull from the database to produce a list of events
class GetEvents{
    private Member a = new Member("Joshua", "Lin");
    private Member b = new Member("Meilin", "Yuan");
    private Member c = new Member("Richard", "Lee");

    public ArrayList<Event> pull(){
        String[] event1 = {"Joshua Lin"};
        Event e = new Event("Test 1", event1);
        e.memberList.add(a);
        e.memberList.add(b);

        String[] event2 = {"Meilin Yuan"};
        Event e2 = new Event("Test 2", event2);
        e2.memberList.add(b);
        e2.memberList.add(c);

        ArrayList<Event> re = new ArrayList<>();
        re.add(e);
        re.add(e2);

        return re;
    }

}

class Member{
    String name;
    int points = 0;
    boolean checkIn = false;

    public Member(String first, String last){
        name = first + " " + last; 
    }
}