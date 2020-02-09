package M_Main;

import M_Main.Shoot.PlayerShoot;
import M_Main.Shoot.Shoot;

import java.awt.*;

public interface EnemyInterface {
    public void move();
    public void draw(Graphics g);
    public Shoot generateShoot();
    public boolean isCollidedWithPlayer(Player player);
    public boolean isCollidedWithShot(PlayerShoot[] shots);
    public void InitCoolTime(int coolTime);
    public int countCoolTime();
}
