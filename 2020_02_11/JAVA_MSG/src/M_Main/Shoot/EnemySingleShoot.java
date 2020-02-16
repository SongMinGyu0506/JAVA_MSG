package M_Main.Shoot;

import M_Main.Player;

import java.awt.*;

public class EnemySingleShoot implements Shoot {

    int xPos;
    int yPos , yPos2;//총알 위치
    final int collision_distance = 3; //충돌 범위 지정

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

    public boolean isCollidedWithPlayer(Player player){
        if (-collision_distance <= (yPos - player.getyPos()) && (yPos - player.getyPos() <= collision_distance)){
            if (-collision_distance <= (xPos - player.getxPos()) && (xPos - player.getxPos() <= collision_distance)){
                return true; //범위 내 충돌시
            }
        } return false;
    }
}
