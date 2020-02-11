package M_Main.Shoot;

import java.awt.*;

public class EnemyBigShot implements Shoot {
    int xPos, yPos;

    public EnemyBigShot(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(xPos,yPos,10,10);
    }

    @Override
    public void move() {
        this.xPos -= 2;
    }

    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos;
    }

    @Override
    public String getName() {
        return "EnemyShoot";
    }
}
