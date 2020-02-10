package M_Main;

import M_Main.Shoot.*;

import java.awt.*;
import java.util.Random;

public class Enemy3 implements EnemyInterface {
    private float xPos;
    private float yPos;
    private float deltaX;
    private float deltaY;
    private int maxX;
    private int maxY;
    private float deltaYInc;
    private int coolTime;
    private final int collision_distance=10;
    Random rand = new Random();

    public Enemy3(float xPos, float yPos, float deltaX, float deltaY, int maxX, int maxY, float deltaYInc) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.deltaYInc = deltaYInc;
    }

    @Override
    public void move() {
        xPos += deltaX;
        yPos += deltaY;


        //todo:기본 이동형 ===> 추후에 패턴재정의 예정
        if (yPos < 0) {
            yPos = 0;
            deltaY = -deltaY;
        } else if(yPos > maxY) {
            yPos = maxY;
            deltaY = -deltaY;
        }
        if(yPos > maxY) {
            yPos = 0;
            deltaY += deltaYInc+1;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillArc((int)xPos,(int)yPos,50,50,50,50);
    }

    @Override
    public Shoot generateShoot() {
        int numRand = rand.nextInt(3);
        System.out.println(numRand);
        Shoot shoot = null;
        if(numRand == 0) {
            shoot = new EnemySingleShoot((int) xPos, (int) yPos);
            //shoot = new EnemyDoubleShot((int)xPos,(int)yPos);
        }
        else if(numRand == 1) {
            shoot = new EnemyTripleShot((int)xPos,(int)yPos);
        }
        else if(numRand == 2) {
            shoot = new EnemyBigShot((int)xPos,(int)yPos);
        }
        return shoot;
    }

    @Override
    public boolean isCollidedWithPlayer(Player player) {
        if (-collision_distance <= (yPos - player.getyPos()) && (yPos - player.getyPos() <= collision_distance)) {
            if (-collision_distance <= (xPos - player.getxPos()) && (xPos - player.getxPos() <= collision_distance)) {
                //collided.
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCollidedWithShot(PlayerShoot[] shots) {
        for (PlayerShoot shot : shots) {
            if (shot == null) {
                continue;
            }
            if (-collision_distance <= (yPos - shot.getYPos()) && (yPos - shot.getYPos() <= collision_distance)) {
                if (-collision_distance <= (xPos - shot.getXPos()) && (xPos - shot.getXPos() <= collision_distance)) {
                    //collided.
                    shot.collided();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void InitCoolTime(int coolTime) {
        this.coolTime=coolTime;
    }

    @Override
    public int countCoolTime() {
        return --coolTime;
    }
}
