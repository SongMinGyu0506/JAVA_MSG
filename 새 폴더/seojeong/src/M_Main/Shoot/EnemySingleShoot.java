package M_Main.Shoot;

import java.awt.*;

public class EnemySingleShoot implements Shoot {

    int xPos;
    int yPos , yPos2;//총알 위치

    //One Shot
    public EnemySingleShoot(int xPos, int yPos){
        this.xPos=xPos;
        this.yPos=yPos;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(xPos,yPos,3,3);
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
        return yPos;
    }

    @Override
    public String getName() {
        return "EnemyShoot";
    }
}
