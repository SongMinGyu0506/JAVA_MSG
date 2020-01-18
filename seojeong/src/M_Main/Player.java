package M_Main;

import java.awt.*;

public class Player {
    private int xPos;
    private int yPos;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    public Player(int x, int y, int minX, int maxX, int minY, int maxY) {
        xPos = x;
        yPos = y;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void moveY(int speed) {
        yPos += speed;
        if( yPos < minY) yPos = minY;
        if( yPos > maxY) yPos = maxY;
    }
    public void moveX(int speed) {
        xPos += speed;
        if( xPos < minX) xPos = minX;
        if( xPos > maxX) xPos = maxX;
    }


    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }


    public void drawPlayer(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(xPos, yPos,10,10);
        /*int[] x_poly = {x_pos, x_pos - 10, x_pos, x_pos + 10};
        int[] y_poly = {y_pos, y_pos + 15, y_pos + 10, y_pos + 15};
        g.fillPolygon(x_poly, y_poly, 4);*/
    }
}
