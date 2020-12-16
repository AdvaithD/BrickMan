import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapView {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapView(int row, int col) {
        map = new int[row][col];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                map[i][j] = 1;
            }
        }

        brickWidth = 540/col;
        brickHeight = 150/row;
    }

    // Level 3
    public void drawThree(Graphics2D g) {
        Color green_blue = new Color(92, 114, 233);
        Color light_black= new Color(48, 48, 48);


        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                if(map[i][j] > 0) {
                    g.setColor(green_blue);
                    g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(light_black);
                    g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                }
            }
        }
    }

    // Level 1
    public void drawOne(Graphics2D g) {
        Color green_blue = new Color(0, 114, 233);
        Color light_black= new Color(48, 48, 48);


        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                if(map[i][j] > 0) {
                    if(j == 3 || i == 3) {
                        g.setColor(green_blue);
                        g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                        g.setStroke(new BasicStroke(3));
                        g.setColor(light_black);
                        g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                    }else if((i == 2 && j == 2) || (i == 1 && j == 2) || (i == 2 && j == 1)) {
                        g.setColor(green_blue);
                        g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                        g.setStroke(new BasicStroke(3));
                        g.setColor(light_black);
                        g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                    }else if((i == 2 && j == 4) || (i == 1 && j == 4) || (i == 2 && j == 5)) {
                        g.setColor(green_blue);
                        g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                        g.setStroke(new BasicStroke(3));
                        g.setColor(light_black);
                        g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                    }else if((i == 4 && j == 2) || (i == 4 && j == 1) || (i == 5 && j == 2)) {
                        g.setColor(green_blue);
                        g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                        g.setStroke(new BasicStroke(3));
                        g.setColor(light_black);
                        g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                    }else if((i == 4 && j == 4) || (i == 4 && j == 5) || (i == 5 && j == 4)) {
                        g.setColor(green_blue);
                        g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                        g.setStroke(new BasicStroke(3));
                        g.setColor(light_black);
                        g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                    }
                    else {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    // Level 2
    public void drawTwo(Graphics2D g) {
        Color green_blue = new Color(92, 114, 0);
        Color light_black= new Color(48, 48, 48);


        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                if((i+j)%2 == 0) {
                    map[i][j] = 0;
                }
                if(map[i][j] > 0) {
                    g.setColor(green_blue);
                    g.fillRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(light_black);
                    g.drawRect(j*brickWidth + 90, i*brickHeight + 60, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int row, int col, int value) {
        map[row][col] = value;
    }

    public void reInitialize() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                map[i][j] = 1;
            }
        }
    }
}
