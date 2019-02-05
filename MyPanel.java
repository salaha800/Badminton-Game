import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{

    // ball coordinates
    int bv,bx,by,bz;

    // player1 handles coordinate
    int p1v,p1x,p1y,p1z;

    // palyer2 handles coordinate
    int p2v,p2x,p2y,p2z;

    // constructor
    public MyPanel( ){

        bv=10;
        bx=10;
        by=200;
        bz=300;

        p1v=10;
        p1x = 10;
        p1y = 180;
        p1z=200;

        p2v=10;
        p2x = 10;
        p2y = 180;
        p2z=200;

    }
    // overriding paintComponent() method to perform custom painting
    public void paintComponent(Graphics g) {

        // erasing any previous painting
        super.paintComponent(g);

        // changing colour and drawing balls
        g.setColor(Color.RED);
        g.fillOval(bx, by,20, 20);
        g.setColor(Color.BLUE);
        g.fillOval(bx, bz,20, 20);
        g.setColor(Color.GREEN);
        g.fillOval(bx, bv,20, 20);

        // changing colour and drawing handles for all players
        g.setColor(Color.YELLOW);
        g.fillRoundRect(p1x, p1y, 20, 100, 20, 20);
        g.fillRoundRect(p2x+getWidth()-20, p2y, 20, 100, 20, 20);
        g.fillRoundRect(p1x, p1z, 20, 100, 20, 20);
        g.fillRoundRect(p2x+getWidth()-20, p2z, 20, 100, 20, 20);
        g.fillRoundRect(p1x, p1v, 20, 100, 20, 20);
        g.fillRoundRect(p2x+getWidth()-20, p2v, 20, 100, 20, 20);
    }

}
