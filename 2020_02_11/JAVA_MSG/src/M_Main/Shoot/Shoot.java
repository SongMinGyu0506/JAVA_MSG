package M_Main.Shoot;

import M_Main.Player;

import java.awt.*;

public interface Shoot {
    void draw(Graphics g);
    void move();
    int getXPos();
    int getYPos();
    String getName();
    public boolean isCollidedWithPlayer(Player player); //다혜


}
