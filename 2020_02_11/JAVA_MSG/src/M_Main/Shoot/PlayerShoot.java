package M_Main.Shoot;

import java.awt.*;

public abstract class PlayerShoot implements Shoot {
    public boolean alive;
    int xPos,yPos; // 총알 위치

    public PlayerShoot(int xPos,int yPos){
        this.xPos=xPos;
        this.yPos=yPos;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(xPos,yPos,3,3);
    }

    @Override
    public void move() {
        xPos+=1;
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
        return "PlayerShoot";
    }

    public void collided() {
        alive = false;
    }
}
