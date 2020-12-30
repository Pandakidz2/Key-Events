public class Main{
    public static void main(String args[]){
        /*Window frame = new Window(700, 700);
        
        frame.panelSetup();
        frame.setVisible(true);
        frame.update();
        */
        
        Request.pull();
        Member Lori = Request.member(2);

        Lori.getMonths();

    }

}
