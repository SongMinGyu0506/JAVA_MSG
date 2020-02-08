package M_Main.Shoot;

import java.awt.*;

public class EnemyTripleShot implements Shoot {
    int xPos;
    int yPos1, yPos2, yPos3;

    public EnemyTripleShot(int xPos, int yPos1) {
        this.xPos = xPos;
        this.yPos1 = yPos1;
        this.yPos2=yPos1-10;
        this.yPos3=yPos1+10;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(xPos,yPos1,3,3);
        g.fillOval(xPos,yPos2,3,3);
        g.fillOval(xPos,yPos3,3,3);
    }

    @Override
    public void move() {
        xPos -= 1;
    }

    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos1;
    }

    @Override
    public String getName() {
        return "EnemyShoot";
    }
}
