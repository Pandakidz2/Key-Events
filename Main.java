import java.util.ArrayList;

public class Main{
    public static void main(String args[]){
        Window frame = new Window(300,200);
        
        Request.pull();
        ArrayList<Member> members = new ArrayList<Member>();
        
        /* Processing and creating so many member objects takes time, needless to say there's
        *  probably a faster way to do this; I'm looking into it.
        */
        for(int i=2; i<Request.getMaxRows()-1; i++){
            members.add(Request.member(i));
        }
        
        frame.setUp(members);
        frame.setVisible(true);

    }

}
