package M_Main;

import java.awt.*;

public class Enemy {
    private float xPos;
    private float yPos;
    private float deltaX;
    private float deltaY;
    private int maxX;
    private int maxY;
    private float deltaYInc;

    public Enemy(float xPos, float yPos, float deltaX, float deltaY, int maxX, int maxY, float deltaYInc) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.deltaYInc = deltaYInc;
    }

    public void move() {
        xPos += deltaX;
        yPos += deltaY;

        if (xPos < 0) {
            xPos = 0;
            deltaX = -deltaX;
        } else if(xPos > maxX) {
            xPos = maxX;
            deltaX = -deltaX;
        }
        if(yPos > maxY) {
            yPos = 0;
            deltaY += deltaYInc;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        int[] xPolyArray = {(int)xPos,(int)xPos-10,(int)xPos,(int)xPos+10};
        int[] yPolyArray = {(int)yPos+15,(int)yPos,(int)yPos+10,(int)yPos};
        g.fillPolygon(xPolyArray,yPolyArray,4);
    }
}
