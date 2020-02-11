package M_Main.Enemy;

import M_Main.Player;
import M_Main.Shoot.PlayerShoot;
import M_Main.Shoot.Shoot;

import java.awt.*;

public interface EnemyInterface {

    //todo Enemy 패키지로 만들어서 뭉쳐야될 것 같음
    public void move();
    public void draw(Graphics g);
    public Shoot generateShoot();
    public boolean isCollidedWithPlayer(Player player);
    public boolean isCollidedWithShot(PlayerShoot[] shots);
    public void InitCoolTime(int coolTime);
    public int countCoolTime();
}
