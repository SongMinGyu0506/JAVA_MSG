package M_Main.Shoot;

import M_Main.Player;

import java.awt.*;

public class EnemyTripleShot implements Shoot {
    int xPos;
    int yPos1, yPos2, yPos3;
    final int collision_distance = 3; //충돌 범위 지정

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

    @Override
    public boolean isCollidedWithPlayer(Player player){
        if (-collision_distance <= (yPos1 - player.getyPos()) && (yPos1 - player.getyPos() <= collision_distance)){
            if (-collision_distance <= (xPos - player.getxPos()) && (xPos - player.getxPos() <= collision_distance)){
                return true; //범위 내 충돌시
            }
        }
        if (-collision_distance <= (yPos2 - player.getyPos()) && (yPos2 - player.getyPos() <= collision_distance)){
            if (-collision_distance <= (xPos - player.getxPos()) && (xPos - player.getxPos() <= collision_distance)){
                return true;
            }
        }
        if (-collision_distance <= (yPos3 - player.getyPos()) && (yPos3 - player.getyPos() <= collision_distance)){
            if (-collision_distance <= (xPos - player.getxPos()) && (xPos - player.getxPos() <= collision_distance)){
                return true;
            }
        } return false;
    }
}
