package M_Main.Enemy;

import M_Main.Player;
import M_Main.Shoot.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Enemy3 implements EnemyInterface {

    //todo Enemy3번 닿으면 안죽음
    private float xPos;
    private float yPos;
    private double deltaX; // MG : 02.19 -> 에너미 이동속도 조절을 위해서 float 에서 double로 수정함
    private double deltaY; // MG : 02.19 -> 에너미 이동속도 조절을 위해서 float 에서 double로 수정함
   private int maxX;
    private int maxY;
    private float deltaYInc;
    private int coolTime;
    private final int collision_distance=10;
    Random rand = new Random();
    ImageIcon imageIcon;



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

        //MG : 2.19 --> 가로축 벽에 부딪힐 시 에너미의 속도가 빨라짐
        if (yPos < 0) {
            yPos = 0;
            deltaY = -deltaY+0.2;
        } else if(yPos > maxY) {
            yPos = maxY;
            deltaY = -deltaY-0.2;
        }
        if(yPos > maxY) {
            yPos = 0;
            deltaY += deltaYInc;
        }
    }
    /*
    *   MG : Enemy 이미지 추가, 임시 이미지 추가했으니 이미지만 바꾸시면 됩니다.
    * */
    @Override
    public void draw(Graphics g) {
        imageIcon = new ImageIcon("./tempMonster.png");
        Image originImg = imageIcon.getImage();
        Image changedImg = originImg.getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(changedImg);
        g.drawImage(newIcon.getImage(),(int)xPos,(int)yPos,null);
        /*g.setColor(Color.MAGENTA);
        g.fillArc((int)xPos,(int)yPos,50,50,50,50);*/
    }

    @Override
    public Shoot generateShoot() {
        int numRand = rand.nextInt(3);
        //System.out.println(numRand);
        Shoot shoot = null;
        shoot = new EnemyBigShot((int)xPos,(int)yPos);
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
