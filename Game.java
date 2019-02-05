import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener{

    JFrame myFrame;
    MyPanel myPanel;

    // labels used to display players name
    JLabel lb1, lb2;

    // to count the score of both players
    int score1;
    int score2;

    // In which direction to move the ball, val is used for this purpose
    int val;

    // used to identify the paddle from which ball is coming
    boolean pad1=false, pad2=false;

    // constructor
    public Game()
    {
        score1 = 0;
        score2 = 0;

        initGUI();
    }

    // used to set layout of the game
    private void initGUI( ) {

        myFrame = new JFrame();
        myPanel = new MyPanel();

        lb1 = new JLabel("Player1 = 0");
        lb2 = new JLabel("Player2 = 0");

        Container c = myFrame.getContentPane();
        c.setLayout(new BorderLayout());

        myPanel.add(lb1);
        myPanel.add(lb2);

        c.add(myPanel);

        myFrame.setSize(500,500);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.addKeyListener(new KeyboradHandler());

        Timer t = new Timer(20,this);
        t.start();

    }

    //*************************************************************************************************
    public void actionPerformed(ActionEvent a)
    {
        determineBallDirection();
        changeBallDirection();

        myPanel.repaint();
    }

    //*************************************************************************************************
    // inner class to handle KeyEvents
    public class KeyboradHandler extends KeyAdapter
    {
        public void keyPressed(KeyEvent ke)
        {

            if(ke.getKeyCode()==ke.VK_DOWN)//move the paddle downward
            {
                if(myPanel.p2y != 365)
                {
                    myPanel.p2y += 5;
                }
            }
            if(ke.getKeyCode()==ke.VK_UP)//move the paddle Upward
            {
                if(myPanel.p2y != 0)
                {
                    myPanel.p2y -= 5;
                }
            }

            if(ke.getKeyCode()==ke.VK_Z)//move the paddle downward
            {
                if(myPanel.p1y != 365)
                {
                    myPanel.p1y += 5;
                }
            }
            if(ke.getKeyCode()==ke.VK_A)//move the paddle Upward
            {
                if(myPanel.p1y != 0)
                {
                    myPanel.p1y -= 5;
                }
            }

        } // end method keyPressed()

    } // end class KeyBoardHandler


    //*****************************************************************************************************
    // method used to determine ball direction
    public void determineBallDirection() {

        ////////////////////***********   code for ball move starts here  *******///////////////
        /* checking is ball going to YMAX from which paddle*/

        if(pad1==true && myPanel.by >= myPanel.getHeight()-15)
        {
            //hit y at upper limit and coming from paddle 2
            val=3;
        }

        else if(pad2==true && myPanel.by >= myPanel.getHeight()-15)
        {
            //hit y at upper limit and coming from paddle 2
            val=2;
        }

        /* checking is ball going to YMIN from which paddle*/
        else if(pad1==true && myPanel.by <= 0)
        {
            val=1;
        }

        else if(pad2==true && myPanel.by <= 0)
        {
            val=4;
        }

        /* *********** checking for the y touch or not *****     */
        //right paddle
        if(myPanel.by > myPanel.p2y+40 && myPanel.by <= myPanel.p2y+80 && myPanel.bx>=(myPanel.getWidth()-40))
        {
            val=4;
            pad2=true;
            pad1=false;
        }

        else if(myPanel.by >= myPanel.p2y && myPanel.by <= myPanel.p2y+40 && myPanel.bx>=(myPanel.getWidth()-40))
        {
            val=2;
            pad2=true;
            pad1=false;
        }

        /*LEFT paddle lower half*/
        else if(myPanel.by >= myPanel.p1y+40 && myPanel.by <= myPanel.p1y+80 && myPanel.bx<=10)
        {
            val=1;
            pad1=true;
            pad2=false;
        }

        /* checking for the LEFT paddle UPPER half after bouncing from YMAX*/
        else if(myPanel.by >= myPanel.p1y && myPanel.by <= myPanel.p1y+40 && myPanel.bx<=10)
        {
            val=3;
            pad1=true;
            pad2=false;
        }

        /* if ball is out ....or any of two (2) paddles  unable to handle */
        if(myPanel.bx >= myPanel.getWidth())
        {
            score1++;
            val=7;
        }

        else if(myPanel.bx < 0)
        {
            score2++;
            val=8;
        }


    } // end determineBallDirection()


    //******************************************************************************************************
    // method is used to change the ball direction
    public void changeBallDirection( )
    {

        switch(val)
        {
            case 1:
                //decrement in x as normal if ball hit paddle before the half of it
                myPanel.bx += 5;
                myPanel.by += 5;

                //System.out.println("case 1........x="+myPanel.bx +              "  y="+myPanel.by);
                break;

            case 2:
                //decrement in y as normal if ball hit LEFT paddle after the half of it
                myPanel.bx -= 5;
                myPanel.by -= 5;

                //System.out.println("case 2........x="+myPanel.bx +              "  y="+myPanel.by);
                break;

            case 3:
                //hit y at upper limit coming from paddle 2
                myPanel.bx += 5;
                myPanel.by -= 5;

                //System.out.println("case 3........x="+myPanel.bx +              "  y="+myPanel.by);
                break;


            case 4:
                //hit y at upper limit coming from paddle 2
                myPanel.bx -= 5;
                myPanel.by += 5;

                //System.out.println("case 4........x="+myPanel.bx +              "  y="+myPanel.by);
                break;

            case 7:
                //cross x upper limit
                myPanel.bx  = 10;
                myPanel.by  = 200;
                myPanel.p2y = 180;
                myPanel.p1y = 180;

                // System.out.println("ball is out side the upper limit of x");
                // System.out.println("case 7........x="+myPanel.bx +              "  y="+myPanel.by);

                lb1.setText("Player 1 ="+ score1 +"");
                break;


            case 8:
                //cross x lower limit
                myPanel.bx = myPanel.getWidth()-40;
                myPanel.by  = 200;
                myPanel.p2y = 180;
                myPanel.p1y = 180;

                // System.out.println("ball is in side the lower limit of x");
                // System.out.println("case 8........x="+myPanel.bx +              "  y="+myPanel.by);

                lb2.setText("Player 2 ="+ score2 +"");
                break;


        }// end switch

    } // end method changeBallDirection()


    //*********************************************************************************************************
    public static void main(String args[]){

        Game game = new Game();

    }

}