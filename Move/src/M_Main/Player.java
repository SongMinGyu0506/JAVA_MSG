package M_Main;

import java.awt.*;

public class Player {
    private int x_pos;
    private int y_pos;
    private int min_x;
    private int max_x;

    public Player(int x, int y, int min_x, int max_x) {
        x_pos = x;
        y_pos = y;
        this.min_x = min_x;
        this.max_x = max_x;
    }

    public void moveX(int speed) {
        y_pos += speed;
        if( y_pos < min_x) y_pos = min_x;
        if( x_pos > max_x) y_pos = max_x;
    }

    public int getX() {
        return x_pos;
    }

    public int getY() {
        return y_pos;
    }


    public void drawPlayer(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x_pos,y_pos,10,10);
        /*int[] x_poly = {x_pos, x_pos - 10, x_pos, x_pos + 10};
        int[] y_poly = {y_pos, y_pos + 15, y_pos + 10, y_pos + 15};
        g.fillPolygon(x_poly, y_poly, 4);*/
    }
}
