


import javax.swing.*;
import java.awt.*;

public class Plnr {
    JFrame win = new JFrame();

    Plnr(){
        win.setTitle("Harmonogram zajeć");
        win.setLayout(new BorderLayout());

        win.add(new ListaTematow(), BorderLayout.CENTER);
        win.setIconImage(Toolkit.getDefaultToolkit().getImage("app.png"));
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(640 , 480);
        win.setVisible(true);

    }
    public static void main(String[] args){
        new Plnr();
    }
}
