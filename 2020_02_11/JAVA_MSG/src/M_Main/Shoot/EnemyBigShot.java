package M_Main.Shoot;

import M_Main.Player;

import java.awt.*;

public class EnemyBigShot implements Shoot {
    int xPos, yPos;
    final int collision_distance = 5; //충돌 범위 지정

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

    @Override
    public boolean isCollidedWithPlayer(Player player){
        if (-collision_distance <= (yPos - player.getyPos()) && (yPos - player.getyPos() <= collision_distance)){
            if (-collision_distance <= (xPos - player.getxPos()) && (xPos - player.getxPos() <= collision_distance)){
                return true; //범위 내 충돌시
            }
        } return false;
    }
}
