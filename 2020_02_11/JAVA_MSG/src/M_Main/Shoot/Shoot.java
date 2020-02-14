package M_Main.Shoot;

import java.awt.*;

public interface Shoot {
    void draw(Graphics g);
    void move();
    int getXPos();
    int getYPos();
    String getName();
    public boolean inCollidedWithPlayer(Player player); //다혜
}
