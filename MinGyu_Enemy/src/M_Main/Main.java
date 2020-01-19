package M_Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main extends JPanel implements Runnable {
    Player player;
    private Thread th;

    private ArrayList enemies;
    private final int enemyMaxDownSpeed = 1;
    private int enemySize;
    private Random rand;
    private final int width = 800;
    private final int height = 400;
    private javax.swing.Timer timer;
    private final int enemyTimeGap = 1000;
    private final int maxEnemySize = 20;
    private final int enemyMaxHorizonSpeed = 1;

    private boolean playerMoveLeft;
    private boolean playerMoveRight;
    private boolean playerMoveGo;
    private boolean playerMoveBack;
    private final int playerLeftSpeed = -2;
    private final int PlayerRightSpeed = 2;
    private final int PlayerGoSpeed = 2;
    private final int PlayerBackSpeed = -2;
    private Image dbImage;
    private Graphics dbg;
    private float enemyMaxDownSpeedInc = 0.3f;

    public Main() {
        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(width,height));
        //player = new Player(width/2,(int)(height * 0.9),10,width-10);
        player = new Player((int)(width*0.03),height/2,10,width-20,10,height-20);
        enemies = new ArrayList();
        enemySize = 0;
        rand = new Random(1);
        timer = new javax.swing.Timer(enemyTimeGap, new addANewEnemy());
        timer.start();
        addKeyListener(new ShipControl());
        setFocusable(true);
    }

    public void start() {
        th = new Thread(this);
        th.start();
    }

    /*
    *   Enemy 생성 클래스,
    *   Random 함수 이용하여 Enemy 속도와 등장 위치 지정
    *   ArrayList 활용하여 등장 Enemy 지정
    * */
    private class addANewEnemy implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(++enemySize <= maxEnemySize) {
                float downspeed;
                do {
                    downspeed = rand.nextFloat() * enemyMaxDownSpeed;
                } while(downspeed == 0);

                float horspeed = rand.nextFloat() * 2 * enemyMaxHorizonSpeed - enemyMaxHorizonSpeed;
                Enemy newEnemy = new Enemy(width,(int)(rand.nextFloat() * height),horspeed,downspeed,width,height,enemyMaxDownSpeedInc);
                enemies.add(newEnemy);
            } else {
                timer.stop();
            }
        }
    }

    public class ShipControl implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    playerMoveLeft = true;
                    break;
                case KeyEvent.VK_DOWN:
                    playerMoveRight = true;
                    break;
                case KeyEvent.VK_LEFT:
                    playerMoveBack = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    playerMoveGo = true;
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    playerMoveLeft = false;
                    break;
                case KeyEvent.VK_DOWN:
                    playerMoveRight = false;
                    break;
                case KeyEvent.VK_LEFT:
                    playerMoveBack = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    playerMoveGo = false;
                    break;
            }
        }
    }
    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        while(true) {
            if(playerMoveLeft) {
                player.moveY(playerLeftSpeed);
            } else if (playerMoveRight) {
                player.moveY(PlayerRightSpeed);
            } else if (playerMoveGo){
                player.moveX(PlayerGoSpeed);
            }  else if(playerMoveBack){
                player.moveX(PlayerBackSpeed);
            }

            /*
            *   Enemy Move
            * */
            Iterator enemyList = enemies.iterator();
            while(enemyList.hasNext()) {
                Enemy enemy = (Enemy) enemyList.next();
                enemy.move();
            }

            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }

            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    public void paintComponent(Graphics g) {
        initImage(g);
        player.drawPlayer(g);
        Iterator enemyList = enemies.iterator();
        while(enemyList.hasNext()) {
            Enemy enemy = (Enemy) enemyList.next();
            enemy.draw(g);
        }
    }

    private void initImage(Graphics g) {
        if(dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        dbg.setColor(getBackground());
        dbg.fillRect(0,0,this.getSize().width,this.getSize().height);//

        dbg.setColor(getForeground());
        g.drawImage(dbImage,0,0,this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main main1 = new Main();
        frame.getContentPane().add(main1);
        frame.pack();
        frame.setVisible(true);
        main1.start();
    }
}
