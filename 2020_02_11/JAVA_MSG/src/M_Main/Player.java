package M_Main;

import M_Main.Shoot.PlayerShoot;

import java.awt.*;

public class Player {
    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    private int xPos;
    private int yPos;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private boolean playerMoveUp;
    private boolean playerMoveDown;
    private boolean playerMoveRight;
    private boolean playerMoveLeft;
    private int playerUpSpeed = -2;   //sj.아이템으로 값이 수정될 경우를 대비하여 final 삭제
    private int PlayerDownSpeed = 2;
    private int PlayerRightSpeed = 2;
    private int PlayerLeftSpeed = -2;

    public boolean isPlayerMoveUp() {
        return playerMoveUp;
    }

    public void setPlayerMoveUp(boolean playerMoveUp) {
        this.playerMoveUp = playerMoveUp;
    }

    public boolean isPlayerMoveDown() {
        return playerMoveDown;
    }

    public void setPlayerMoveDown(boolean playerMoveDown) {
        this.playerMoveDown = playerMoveDown;
    }

    public boolean isPlayerMoveRight() {
        return playerMoveRight;
    }

    public void setPlayerMoveRight(boolean playerMoveRight) {
        this.playerMoveRight = playerMoveRight;
    }

    public boolean isPlayerMoveLeft() {
        return playerMoveLeft;
    }

    public void setPlayerMoveLeft(boolean playerMoveLeft) {
        this.playerMoveLeft = playerMoveLeft;
    }
    public int getPlayerUpSpeed() {
        return playerUpSpeed;
    }

    public void setPlayerUpSpeed(int playerUpSpeed) {
        this.playerUpSpeed = playerUpSpeed;
    }

    public int getPlayerDownSpeed() {
        return PlayerDownSpeed;
    }

    public void setPlayerDownSpeed(int playerDownSpeed) {
        PlayerDownSpeed = playerDownSpeed;
    }

    public int getPlayerRightSpeed() {
        return PlayerRightSpeed;
    }
    public void setPlayerRightSpeed(int playerRightSpeed) {
        PlayerRightSpeed = playerRightSpeed;
    }
    public int getPlayerLeftSpeed() {
        return PlayerLeftSpeed;
    }
    public void setPlayerLeftSpeed(int playerLeftSpeed) {
        PlayerLeftSpeed = playerLeftSpeed;
    }
    public Player(int x, int y, int minX, int maxX, int minY, int maxY) {
        xPos = x;
        yPos = y;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
    public void moveY(int speed) {
        yPos += speed;
        if( yPos < minY) yPos = minY;
        if( yPos > maxY) yPos = maxY;
    }
    public void moveX(int speed) {
        xPos += speed;
        if( xPos < minX) xPos = minX;
        if( xPos > maxX) xPos = maxX;
    }

    public void drawPlayer(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(xPos, yPos,10,10);
    }

    //플레이어 총알 생성
    public PlayerShoot generateShoot(){
        PlayerShoot shoot=new PlayerShoot(xPos,yPos);

        return shoot;
    }
}
