import java.util.ArrayList;

public class Main{
    public static void main(String args[]){
        Window frame = new Window(300,200);
        
        Request.pull();
        ArrayList<Member> members = new ArrayList<Member>();
        members.add(Request.member(2));
        members.add(Request.member(3));
        
        frame.setUp(members);
        frame.setVisible(true);

    }

}
