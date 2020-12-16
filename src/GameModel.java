import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.swing.Timer;
import javax.swing.JPanel;


public class GameModel extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private boolean gameOver = false;
    private boolean quit = false;


    private int score = 0;
    private int finalScore = 0;
    int prevHighScore = 0;
    int highScore = 0;


    ArrayList<Integer> highScores = new ArrayList<>();

    int levelUp = 1;
    private Timer timer;
    private int delay = 5;
    private int playerX = 310;
    private int ballposX = 180;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapView map;

    public GameModel() {
        // create the grid of bricks to break
        map = new MapView(7, 7);
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

        // Map Based On Levels
        if(levelUp == 1) {
            map.drawOne((Graphics2D)g);
        }else if(levelUp == 2) {
            map.drawTwo((Graphics2D)g);
        }else if(levelUp == 3) {
            map.drawThree((Graphics2D)g);
        }

        // the user controller
        g.setColor(light_red);
        g.fillRect(playerX, 500, 160, 9);

        // the paddle ball
        g.setColor(Color.white);
        g.fillOval(ballposX, ballposY, 14, 14);

        // scores
        if(highScores.isEmpty()) {
            g.drawString("High Score: " + this.highScore + " Current Score: " + score, 500, 615);
        }else {
            int scoreIndex = highScores.size() - 1;
            g.drawString("High Score: " + highScores.get(scoreIndex) + " Current Score: " + score, 500, 615);
        }


        // Exit to main screen
        g.drawString("Press 'Q' to exit to welcome screen, 'P' to Pause Game, Enter for new game", 20, 615);

        // Game Over
        if(gameOver) {
            if(finalScore > highScores.get(highScores.size()-1)) {
                g.setColor(Color.gray);
                g.fillRoundRect(150, 260, 400, 180, 60, 60);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                g.drawString("GAME OVER", 285, 330);
                g.drawString("NEW HIGH SCORE: " + highScore, 245, 365);
                g.drawString("PRESS 'Q' TO GO HOME", 230, 400);
            }
            else {
                g.setColor(Color.gray);
                g.fillRoundRect(150, 260, 400, 180, 60, 60);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                g.drawString("GAME OVER", 285, 330);
                g.drawString("YOUR SCORE: " + finalScore, 260, 365);
                g.drawString("PRESS 'Q' TO GO HOME", 230, 400);
            }
        }
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play) {
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 500, 160, 9))) {
                ballYdir = -ballYdir;
            }

            int sum = 0;

            for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map[0].length; j++) {
                    // Deal with the intersection with the bricks here.
                    sum += map.map[i][j];
                    if(map.map[i][j] == 1) {
                        Rectangle brick = new Rectangle(j * map.brickWidth + 90, i*map.brickHeight + 60, map.brickWidth, map.brickHeight);
                        Rectangle ball = new Rectangle(ballposX, ballposY, 14, 14);
                        if(ball.intersects(brick)) {
                            score += 10;
                            ballYdir = -ballYdir;
                            map.map[i][j] = map.map[i][j] - 1;
                            repaint();
                        }
                    }
                }
            }

            if(sum == 0) {
                map.reInitialize();

                if(levelUp == 3) {
                    levelUp = 1;
                    int temp = this.delay - 1;
                    timer = new Timer(temp, this);
                    timer.start();
                }
                else {
                    levelUp++;
                }

            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if(ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if(ballposX > 670) {
                ballXdir = -ballXdir;
            }
            if(ballposY > 620) {
                play = false;
                gameOver = true;
                finalScore = score;
                prevHighScore = highScore;
                highScore = Math.max(score, highScore);
                highScores.add(prevHighScore);
                score = 0;
                delay = 5;
            }
        }
        repaint(); // called automatically, recreates game state whenever a user action is performed
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 530) {
                playerX = 530;
            }else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 5) {
                playerX = 2;
            }else {
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_Q) {
            quit = true;
            play = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_P) {
            play = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                play = true;
                quit = false;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                map = new MapView(7, 7);
                repaint();
            }
        }

    }

    public void moveRight() {
        if(!gameOver) {
            play = true;
            playerX += 40;
        }
    }

    public void moveLeft() {
        if(!gameOver) {
            play = true;
            playerX -= 40;
        }
    }
}