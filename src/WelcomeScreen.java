import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WelcomeScreen extends JPanel implements ActionListener, KeyListener{
    private int ballposX = 180;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    boolean play = false;

    private Timer timer;

    private int delay = 5;

    public WelcomeScreen() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {

        Color light_red = new Color(255, 165, 165);
        Color light_grey = new Color(224, 224, 224);
        Color light_black= new Color(48, 48, 48);

        // Bottom Background
        g.setColor(Color.gray);
        g.fillRect(1, 1, 700, 650);

        // Background
        g.setColor(light_black);
        g.fillRect(1, 1, 692, 592);

        // Border
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        g.fillRect(3, 589, 688, 3); // Bottom Border

        // Ball
        g.setColor(Color.white);
        g.fillOval(ballposX, ballposY, 14, 14);

        // Exit to main screen
        g.drawString("BrickMan", 250, 250);
        g.drawString("Start using arrows to begin playing", 235, 275);

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        ballposX += ballXdir;
        ballposY += ballYdir;
        if(ballposX < 0) {
            ballXdir = -ballXdir;
        }
        if(ballposY < 0) {
            ballYdir = -ballYdir;
        }
        if(ballposX > 677) {
            ballXdir = -ballXdir;
        }
        if(ballposY > 577) {
            ballYdir = -ballYdir;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}