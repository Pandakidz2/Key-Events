import java.util.ArrayList;

public class Member{
    private String firstName;
    private String lastName;
    private ArrayList<Month> months = new ArrayList<Month>();

    // Accessor Methods
    public String name(){
        return lastName + ", " + firstName;
    }
    public void getMonths(){
        for(int i=0; i<months.size(); i++){
            System.out.println(months.get(i).getName());
            months.get(i).getEvents();

            System.out.println("\n\n");
        }
    }

    // Mutator Methods
    public void setFirst(String first){
        this.firstName = first;
    }
    public void setLast(String last){
        this.lastName = last;
    }
    public void addMonth(Month m){
        this.months.add(0, m);
    }
}

class Month{
    private String monthName;
    private ArrayList<Event> events = new ArrayList<Event>();

    public Month(String name){
        this.monthName = name;
    }

    public void addEvent(Event e){
        events.add(0, e);
    }

    public String getName(){
        return monthName;
    }

    public void getEvents(){
        for(int i=0; i<events.size(); i++){
            System.out.println(events.get(i).getEvent());
        }
    }
}

class Event{
    private String name;
    private String points;

    public Event(String n, String p){
        this.name = n;
        this.points = p;
    }

    // Accessor methods
    public String getEvent(){
        return getName() + " -- " + getPoints();
    }
    private String getName(){
        return name;
    }
    private String getPoints(){
        return points;
    }
}